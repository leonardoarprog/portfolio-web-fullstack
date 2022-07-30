package ar.com.portfolioweb.backendspringboot.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ar.com.portfolioweb.backendspringboot.seguridad.jwt.JwtEntryPoint;
import ar.com.portfolioweb.backendspringboot.seguridad.jwt.JwtTokenFilter;
import ar.com.portfolioweb.backendspringboot.seguridad.servicio.UserDetailsServicio;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSeguridad extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServicio userDetailsServiceImpl;

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/usuarios/username/{nombreUsuario}").permitAll()
                .antMatchers("/api/usuarios/username/{id}").permitAll()
                .antMatchers("/api/usuarios/datos_personales/sobre_mi/obtener/{nombreUsuario}").permitAll()
                .antMatchers("/api/usuarios/datos_personales/foto_perfil/obtener/{nombreUsuario}").permitAll()
                .antMatchers("/api/usuarios/datos_personales/img_bg/obtener/{nombreUsuario}").permitAll()
                .antMatchers("/api/usuarios/exp_laborales/obtener/{nombreUsuario}").permitAll()
                .antMatchers("/api/usuarios/habilidades/obtener/{nombreUsuario}").permitAll()
                .antMatchers("/api/usuarios/educaciones/obtener/{nombreUsuario}").permitAll()
                .antMatchers("/api/usuarios/proyectos/obtener/{nombreUsuario}").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}