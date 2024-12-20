package com.iamdinuth.commonmicroservice.data.service;

import com.iamdinuth.commonmicroservice.data.entity.Client;
import com.iamdinuth.commonmicroservice.data.entity.User;
import com.iamdinuth.commonmicroservice.data.repository.AutowireRepositories;
import com.iamdinuth.commonmicroservice.exception.BadInputError;
import com.iamdinuth.commonmicroservice.graphql.generated.DgsConstants;
import com.iamdinuth.commonmicroservice.data.types.ClientIdInput;
import com.iamdinuth.commonmicroservice.data.types.MutationResponse;
import com.iamdinuth.commonmicroservice.data.types.UserInput;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import graphql.execution.ResultPath;
import graphql.language.SourceLocation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService extends AutowireRepositories {

    public List<User> findAll(DgsDataFetchingEnvironment dfe) {
        return userRepository.findAll(egb.build(dfe));
    }

    public User saveUser(UserInput userInput, DgsDataFetchingEnvironment dfe) throws BadInputError {
        if (userInput.getUserId() == null){
            // Create User
            User user = new User();
            user.setFirstName(userInput.getFirstName());
            user.setLastName(userInput.getLastName());
            user.setEmail(userInput.getEmail());
            user.setPhone(userInput.getPhone());
            return userRepository.save(user);
        }
        else {
            // Update twin
            Optional<User> userById = userRepository.findById(userInput.getUserId());
            if (userById.isPresent()){
                User user =  userById.get();
                user.setFirstName(userInput.getFirstName());
                user.setLastName(userInput.getLastName());
                user.setEmail(userInput.getEmail());
                user.setPhone(userInput.getPhone());
                return userRepository.save(user);
            } else {
                List<SourceLocation> locations =  List.of(dfe.getField().getSourceLocation());
                ResultPath path = dfe.getExecutionStepInfo().getPath();
                throw new BadInputError("Bad Input: userInput.userId", locations, path);
            }
        }
    }


//    public List<User> findUsersForClient(UUID clientId, DgsDataFetchingEnvironment dfe) {
//        return userRepository.findUsersForClient(clientId, egb.build(dfe));
//    }

    public MutationResponse deleteUser(UUID userId, DgsDataFetchingEnvironment dfe){
        try {
            // Check if the user exists
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                return new MutationResponse(false, "User not found.", 404);
            }
            // Delete the user
            userRepository.delete(user);
            return new MutationResponse(true, "User deleted successfully.", 200);
        } catch (Exception e) {
            return new MutationResponse(false, "Error deleting user: " + e.getMessage(), 500);
        }
    }

}
