package com.flowforge.user.config;

import com.flowforge.user.security.OrgInterceptor;
import com.flowforge.user.security.RoleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RoleInterceptor roleInterceptor;
    private final OrgInterceptor orgInterceptor;

    public WebConfig(RoleInterceptor roleInterceptor, OrgInterceptor orgInterceptor) {
        this.roleInterceptor = roleInterceptor;
        this.orgInterceptor = orgInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 🔐 Order matters
        registry.addInterceptor(orgInterceptor);
        registry.addInterceptor(roleInterceptor);
    }
}
