import api.PaymentRemitInfoModel;
import api.RequestObject;
import api.ResponseObject;
import api.TransferEndpoint;
import kong.unirest.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MoneyTransferTest {
    PaymentRemitInfoModel paymentRemitInfoModel;
    TransferEndpoint transferEndpoint;
    HashMap requestBody;
    SoftAssert softAssert;

    @DataProvider()
    public Object[][] dpCountriesAndLevels(){
        paymentRemitInfoModel = transferEndpoint.getRemitInfoModel(new HashMap());
        List<String> countries = paymentRemitInfoModel.getCountry();
        String[] serviceArr = {"1","2","3"};
        Object[][] returnCountries = new Object[countries.size()*serviceArr.length][2];
        int t = 0;
        for (int i = 0; i < serviceArr.length; i++) {
            if(t==105){
                break;
            }
            for (int j = 0; j <countries.size() ; j++) {
                returnCountries[t][0] = countries.get(j);
                returnCountries[t][1] = serviceArr[i];
                t++;
            }

        }
        return returnCountries;
    }

    @BeforeClass
    public void beforeClass(){
        transferEndpoint = new TransferEndpoint();
        requestBody = new HashMap();
        softAssert = new SoftAssert();
    }

    @Test(dataProvider = "dpCountriesAndLevels")
    public void toBankAccount(String country,String serviceLevel){
        RequestObject requestObject = new RequestObject(
                country,
                "Account",
                "100",
                serviceLevel
        );
        requestObject.requestPrettyPrint();
        ResponseObject responseObject = transferEndpoint.getRemittanceChargeRate(requestObject.getRequestModel());
        Assert.assertEquals(responseObject.getCode(),200);
        System.out.println("Step1 passed");
        Assert.assertFalse(responseObject.isBeneficiaryFieldsEmpty());
        System.out.println("Step2 passed");
        Assert.assertTrue(responseObject.getDestinationAmount()>0);
        System.out.println("Step3 passed");
        Assert.assertTrue(responseObject.getRate()>0);
        System.out.println("Step4 passed");
        Assert.assertTrue(responseObject.getTotalCharge()>0);
        System.out.println("Step5 passed");
        Assert.assertTrue(responseObject.isPointsValid());
        System.out.println("Step6 passed");
        //softAssert.assertAll();
    }

    @Test(dataProvider = "dpCountriesAndLevels")
    public void toCashCollection(String country,String serviceLevel){
        RequestObject requestObject = new RequestObject(
                country,
                "Cash Collection",
                "100",
                serviceLevel
        );
        requestObject.requestPrettyPrint();
        ResponseObject responseObject = transferEndpoint.getRemittanceChargeRate(requestObject.getRequestModel());
        Assert.assertEquals(responseObject.getCode(),200);
        System.out.println("Step1 passed");
        Assert.assertFalse(responseObject.isBeneficiaryFieldsEmpty());
        System.out.println("Step2 passed");
        Assert.assertTrue(responseObject.getDestinationAmount()>0);
        System.out.println("Step3 passed");
        Assert.assertTrue(responseObject.getRate()>0);
        System.out.println("Step4 passed");
        Assert.assertTrue(responseObject.getTotalCharge()>0);
        System.out.println("Step5 passed");
        Assert.assertTrue(responseObject.isPointsValid());
        System.out.println("Step6 passed");
        //softAssert.assertAll();
    }

    @Test(dataProvider = "dpCountriesAndLevels")
    public void toBankCard(String country,String serviceLevel){
        RequestObject requestObject = new RequestObject(
                country,
                "Bank Card",
                "100",
                serviceLevel
        );
        requestObject.requestPrettyPrint();
        ResponseObject responseObject = transferEndpoint.getRemittanceChargeRate(requestObject.getRequestModel());
        Assert.assertEquals(responseObject.getCode(),200);
        System.out.println("Step1 passed");
        Assert.assertFalse(responseObject.isBeneficiaryFieldsEmpty());
        System.out.println("Step2 passed");
        Assert.assertTrue(responseObject.getDestinationAmount()>0);
        System.out.println("Step3 passed");
        Assert.assertTrue(responseObject.getRate()>0);
        System.out.println("Step4 passed");
        Assert.assertTrue(responseObject.getTotalCharge()>0);
        System.out.println("Step5 passed");
    }

    @Test()
    public void toBankAccounts1(){
        RequestObject requestObject = new RequestObject(
                "CIV",
                "Account",
                "100",
                "1"
        );
        ResponseObject responseObject = transferEndpoint.getRemittanceChargeRate(requestObject.getRequestModel());
        softAssert.assertEquals(responseObject.getCode(),200);
        softAssert.assertFalse(responseObject.isBeneficiaryFieldsEmpty());
        softAssert.assertTrue(responseObject.getDestinationAmount()>0);
        softAssert.assertTrue(responseObject.getRate()>0);
        softAssert.assertTrue(responseObject.getTotalCharge()>0);
        softAssert.assertTrue(responseObject.isPointsValid());
        softAssert.assertAll();
    }

}
