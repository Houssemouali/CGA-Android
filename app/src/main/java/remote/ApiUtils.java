package com.esprit.insurance.insurance;

public class ApiUtils {

    public static final String BASE_URL = "http://7db2b198.ngrok.io/cga-web/api/";

    public static InsuranceService getInsuranceService() {
        return RetrofitClient.getClient(BASE_URL).create(InsuranceService.class);
    }
}
