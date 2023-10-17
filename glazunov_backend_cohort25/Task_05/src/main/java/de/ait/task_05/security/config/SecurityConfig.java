package de.ait.task_05.security.config;

import de.ait.task_05.security.details.AuthenticatedParticipant;
import de.ait.task_05.security.details.ParticipantDetailsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception { // httpSecurity - это безопасность Spring
        httpSecurity.csrf().disable(); // отключаем CSRF, принять как данность, иначе работать ничего не будет
        httpSecurity.headers().frameOptions().disable(); // аналогично пункту выше

        httpSecurity
                .authorizeRequests()
                .antMatchers ("/swagger-ui/**").permitAll() // разрешаем всем доступ к Swagger
                .antMatchers(HttpMethod.POST, "/api/users/register/**").permitAll() // разрешаем POST-запрос на регистрацию
                .antMatchers("/api/**").authenticated(); // разрешаем доступ для всех остальных endpoints только аутентифицированным пользователям

        httpSecurity
                .exceptionHandling() // настраиваем собственную обработку ошибок безопасности
                .defaultAuthenticationEntryPointFor(((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // задаем в ответе статус 401
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE); // говорим, что в ответе будет JSON
                    response.getWriter().write("{ \"message\": \"User unauthorized\"}"); // пишем JSON-ответ
                }), new AntPathRequestMatcher("/api/**")); // указываем, что эта обработка будет работать на всех endpoint-ах, начинающихся с api

        httpSecurity
                .formLogin(); // включили страницу с входом по адресу /login, которая уже в Spring Security
        return httpSecurity.build(); // собираем цепочку безопасности
    }


    @Autowired
    public void bindParticipantDetailServiceAndPasswordEncoder(UserDetailsService userDetailsServiceImpl,
                                                               PasswordEncoder passwordEncoder,
                                                               AuthenticationManagerBuilder builder) throws Exception { // не работало Exeption
        builder.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder);
    }

}
