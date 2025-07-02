package az.turing.jobportal.utility;

import az.turing.jobportal.entity.Sequence;
import az.turing.jobportal.exception.JobPortalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class Utilities {
    private static MongoOperations mongoOperations;
    @Autowired
    public void setMongoOperations(MongoOperations mongoOperations) {
        Utilities.mongoOperations = mongoOperations;
    }
    public static Long getNextSequence(String key) throws JobPortalException{
        Query query = new Query(Criteria.where("_id").is(key));
        Update update = new Update().inc("seq", 1);
        update.inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        Sequence sequence = mongoOperations.findAndModify(query, update, options, Sequence.class);
        if (sequence == null) throw new JobPortalException("Sequence not found for key: " + key);
        return sequence.getSeq();
    }
    public static String generateOtp() {
        StringBuilder otp = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for(int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10)); // Generates a random digit between 0 and 9
        }
        return otp.toString();
    }
}
