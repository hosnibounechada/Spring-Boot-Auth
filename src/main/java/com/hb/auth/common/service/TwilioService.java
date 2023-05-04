package com.hb.auth.common.service;

import com.hb.auth.exception.NotFoundException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TwilioService {
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.service.sid}")
    private String serviceSid;

    @Value("${twilio.phone.number}")
    private String phoneNumber;

    /**
     * Send Phone message to client by Twilio Number
     *
     * @param to String
     * @return Boolean
     */
    public boolean sendMessage(String to) {
        Twilio.init(accountSid, authToken);

        Message.creator(new PhoneNumber(to),
                new PhoneNumber(to),
                "Welcome client ").create();

        return true;
    }

    /**
     * Send 6 Digits code from Twilio to Client phone
     *
     * @param to String
     * @return Boolean
     */

    public boolean sendOTP(String to) {
        Twilio.init(accountSid, authToken);
        Verification.creator(
                        serviceSid, // this is your verification sid
                        to, //this is your Twilio verified recipient phone number
                        "sms") // this is your channel type
                .create();
        return true;
    }

    /**
     * Verify the 6 Digits Code provided by Twilio Service
     *
     * @param phone String
     * @param otp   String
     * @return Boolean
     */
    public boolean verifyOTP(String phone, String otp) {
        Twilio.init(accountSid, authToken);

        try {

            VerificationCheck.creator(
                            serviceSid)
                    .setTo(phone)
                    .setCode(otp)
                    .create();

        } catch (Exception e) {
            throw new NotFoundException("Bad Request");
        }
        return true;
    }
}
