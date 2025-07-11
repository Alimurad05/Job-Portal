package az.turing.jobportal.repo;

import az.turing.jobportal.entity.OTP;
import az.turing.jobportal.exception.JobPortalException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPRepository extends MongoRepository<OTP, String> {

}
