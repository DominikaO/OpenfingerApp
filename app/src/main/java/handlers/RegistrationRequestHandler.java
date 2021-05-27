package handlers;

import java.util.HashMap;
import java.util.Map;

import OpenFinger.RegistrationRequestOuterClass;

public class RegistrationRequestHandler {
    int id;
    Map<RegistrationRequestOuterClass.RegistrationRequest, Boolean> requests = new HashMap<>();

    public Map<RegistrationRequestOuterClass.RegistrationRequest, Boolean> getRequests() {
        return requests;
    }

    public RegistrationRequestHandler(int id) {
        this.id = id;
    }

    public void addRequest(RegistrationRequestOuterClass.RegistrationRequest registrationRequest){
        requests.put(registrationRequest,false);
    }

    public void removeRequest(RegistrationRequestOuterClass.RegistrationRequest request){
        for (RegistrationRequestOuterClass.RegistrationRequest reg : requests.keySet()){
            if (reg.equals(request)) requests.remove(request);
        }
    }

    public void setRequestsStatus(int registrationId){

        for (Map.Entry<RegistrationRequestOuterClass.RegistrationRequest, Boolean> entry : requests.entrySet()){
            if (entry.getKey().getRequestId() == registrationId) entry.setValue(true);
        }
    }

    public int getId() {
        return requests.size();
    }

    public void setId(int id) {
        this.id = id;

    }

    public void plusId(){
        this.id++;
    }
}
