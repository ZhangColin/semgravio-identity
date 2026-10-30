package com.semgravio.semgravioidentity.sso.infrastructure.verification;

import org.springframework.stereotype.Component;

import com.semgravio.semgravioidentity.verification.application.VerificationCodeAppService;
import com.semgravio.semgravioidentity.verification.application.dto.VerifyCodeCommand;
import com.semgravio.semgravioidentity.verification.application.dto.VerifySmsCodeCommand;
import com.cartisan.core.stereotype.Adapter;
import com.cartisan.core.stereotype.PortType;

/**
 * 验证码适配器——适配 verification 上下文的 {@link VerificationCodeAppService}，
 * 实现 sso 上下文的 {@link VerificationCodePort}。
 *
 * <p>类名带 Sso 前缀：与 account 上下文同名适配器区分 Spring 默认 bean 名。</p>
 *
 * @since 0.1.0
 */
@Adapter(PortType.CLIENT)
@Component
public class SsoVerificationCodeAdapter implements VerificationCodePort {

    private final VerificationCodeAppService verificationCodeAppService;

    public SsoVerificationCodeAdapter(VerificationCodeAppService verificationCodeAppService) {
        this.verificationCodeAppService = verificationCodeAppService;
    }

    @Override
    public void verifyCode(String email, String code, String purpose) {
        verificationCodeAppService.verifyCode(new VerifyCodeCommand(email, code, purpose));
    }

    @Override
    public void verifyPhoneCode(String phone, String code, String purpose) {
        verificationCodeAppService.verifyPhoneCode(new VerifySmsCodeCommand(phone, code, purpose));
    }
}
