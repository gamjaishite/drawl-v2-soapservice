package org.soapService.Services;

import io.github.cdimascio.dotenv.Dotenv;
import org.soapService.Common.HTTPStatusCode;
import org.soapService.Common.ServiceResponse;
import org.soapService.Domain.AccountVerificationRequest;
import org.soapService.Domain.GetAllResponse;
import org.soapService.Exceptions.RequestException;
import org.soapService.Exceptions.ValidationException;
import org.soapService.Repository.AccountVerificationRequestRepository;
import org.soapService.Utils.Email;
import org.soapService.Validations.AccountVerificationRequestValidation;

import javax.jws.WebService;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.xml.ws.soap.SOAPFaultException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebService(endpointInterface = "org.soapService.Services.AccountVerificationRequestService")
public class AccountVerificationRequestServiceImpl extends BaseService implements AccountVerificationRequestService {
    private static AccountVerificationRequestRepository accountVerificationRepository = new AccountVerificationRequestRepository();
    private static AccountVerificationRequestValidation accountVerificationServiceValidation = new AccountVerificationRequestValidation();

    public ServiceResponse<GetAllResponse<AccountVerificationRequest>> getAccountVerificationRequests(Integer page,
                                                                                                      Integer pageSize)
            throws SOAPFaultException {
        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 20;
        }

        List<GetAllResponse<AccountVerificationRequest>> lru = new ArrayList<>();
        try {
            lru.add(accountVerificationRepository.getAll(page, pageSize));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        }

        ServiceResponse<GetAllResponse<AccountVerificationRequest>> response = new ServiceResponse<>();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Successfully get all account verification requests");
        response.setData(lru);

        lru.forEach((item) -> {
            item.getData().forEach((data) -> {
                System.out.println(data.getId());
            });
        });

        return response;
    }

    public ServiceResponse<AccountVerificationRequest> createAccountVerificationRequest(String userId)
            throws SOAPFaultException {
        List<AccountVerificationRequest> lru = new ArrayList<>();
        try {
            accountVerificationServiceValidation.validateCreateVerificationRequest(userId);

            AccountVerificationRequest accountVerificationRequest = new AccountVerificationRequest();
            accountVerificationRequest.setUserId(userId);
            accountVerificationRepository.add(accountVerificationRequest);

            lru.add(accountVerificationRequest);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
            if (e.getSQLState().equals("23000")) {
                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(),
                        "This user already have a request");
            } else {
                new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                        "Something went wrong, please try again later");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        }

        ServiceResponse<AccountVerificationRequest> response = new ServiceResponse<>();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Account verification request successfully created");
        response.setData(lru);

        return response;
    }

    public ServiceResponse<AccountVerificationRequest> acceptAccountVerificationRequest(String userId, String email)
            throws SOAPFaultException {
        List<AccountVerificationRequest> lru = new ArrayList<>();
        try {
            accountVerificationServiceValidation.validateAcceptVerificationRequest(userId, email);
            AccountVerificationRequest accountVerificationRequest = new AccountVerificationRequest();
            accountVerificationRequest.setUserId(userId);
            accountVerificationRequest.setStatus("ACCEPTED");

            int res = accountVerificationRepository.update(accountVerificationRequest);
            if (res == 0) {
                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(),
                        "This user doesn't have a request or already verified");
            }

            // Send success email
            Dotenv dotenv = Dotenv.load();

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(dotenv.get("EMAIL_ADDRESS"), dotenv.get("EMAIL_APP_PASSWORD"));
                }
            };
            Session session = Session.getInstance(props, auth);

            Email.send(session, "DQ Admin", email, "Drawl Purple", "Congratulation! You're now titled as Drawl Purple User 🎉 <br/> Enjoy the benefits! Visit <b>DQ</b> now! <br/> Best regards, DQ Teams.");

        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
            if (e.getSQLState().equals("23000")) {
                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(),
                        "This user already have a request");
            } else {
                new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                        "Something went wrong, please try again later");
            }
        } catch (SOAPFaultException e) {
            if (e.getFault().getFaultCode().equals(HTTPStatusCode.BAD_REQUEST.getCodeStr())) {
                throw e;
            }
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        }

        ServiceResponse<AccountVerificationRequest> response = new ServiceResponse<>();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Account verification request with user ID " + userId + " successfully accepted");
        response.setData(lru);

        return response;
    }

    public ServiceResponse<AccountVerificationRequest> rejectAccountVerificationRequest(String userId)
            throws SOAPFaultException {
        List<AccountVerificationRequest> lru = new ArrayList<>();
        try {
            accountVerificationServiceValidation.validateRejectVerificationRequest(userId);
            AccountVerificationRequest accountVerificationRequest = new AccountVerificationRequest();
            accountVerificationRequest.setUserId(userId);
            accountVerificationRequest.setStatus("REJECTED");

            int res = accountVerificationRepository.update(accountVerificationRequest);
            if (res == 0) {
                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(),
                        "This user doesn't have a request or already verified");
            }

            int deleteRes = accountVerificationRepository.delete(accountVerificationRequest.getUserId());
            if (deleteRes == 0) {
                System.out.println("Failed to delete request");
            } else {
                System.out.println("Successfully delete request");
            }
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
            if (e.getSQLState().equals("23000")) {
                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(),
                        "This user already have a request");
            } else {
                new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                        "Something went wrong, please try again later");
            }
        } catch (SOAPFaultException e) {
            if (e.getFault().getFaultCode().equals(HTTPStatusCode.BAD_REQUEST.getCodeStr())) {
                throw e;
            }
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        }

        ServiceResponse<AccountVerificationRequest> response = new ServiceResponse<>();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Account verification request with user ID " + userId + " successfully rejected");
        response.setData(lru);

        return response;
    }

    public ServiceResponse<AccountVerificationRequest> deleteAccountVerificationRequest(int requestId)
            throws SOAPFaultException {
        List<AccountVerificationRequest> lru = new ArrayList<>();
        try {
            accountVerificationServiceValidation.validateDeleteVerificationRequest(requestId);

            int res = accountVerificationRepository.delete(requestId);

            if (res == 0) {
                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(),
                        "This request doesn't exist");
            }
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(), e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
            if (e.getSQLState().equals("23000")) {
                new RequestException(HTTPStatusCode.BAD_REQUEST.getCodeStr(),
                        "This user already have a request");
            } else {
                new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                        "Something went wrong, please try again later");
            }
        } catch (SOAPFaultException e) {
            if (e.getFault().getFaultCode().equals(HTTPStatusCode.BAD_REQUEST.getCodeStr())) {
                throw e;
            }
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new RequestException(HTTPStatusCode.INTERNAL_SERVER_ERROR.getCodeStr(),
                    "Something went wrong, please try again later");
        }

        ServiceResponse<AccountVerificationRequest> response = new ServiceResponse<>();
        response.setStatus(HTTPStatusCode.OK.getCodeStr());
        response.setMessage("Account verification request with ID " + requestId + " successfully deleted");
        response.setData(lru);

        return response;
    }
}
