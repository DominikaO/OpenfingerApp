package handlers;

import java.util.HashMap;
import java.util.Map;

import OpenFinger.VerificationRequestOlejarnikovaOuterClass;
import OpenFinger.VerificationRequestOuterClass;

public class VerificationHandler {
    int id;
    Map<VerificationRequestOlejarnikovaOuterClass.VerificationRequestOlejarnikova, Boolean> requests = new HashMap<>();

    public int getId() {
        return requests.size();
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<VerificationRequestOlejarnikovaOuterClass.VerificationRequestOlejarnikova, Boolean> getRequests() {
        return requests;
    }

    public void addRequests(VerificationRequestOlejarnikovaOuterClass.VerificationRequestOlejarnikova verification) {
        requests.put(verification, false);

    }

    public VerificationHandler(Map<VerificationRequestOlejarnikovaOuterClass.VerificationRequestOlejarnikova, Boolean> requests) {
        this.requests = requests;
    }

    public void removeRequest(VerificationRequestOuterClass.VerificationRequest request){

        for (VerificationRequestOlejarnikovaOuterClass.VerificationRequestOlejarnikova verification: requests.keySet()){
            if (verification.equals(request)) requests.remove(request);
        }
    }


    public VerificationHandler(int id) {
        this.id = id;
    }
}
