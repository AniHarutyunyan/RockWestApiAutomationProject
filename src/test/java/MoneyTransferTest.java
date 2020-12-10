import api.PaymentRemitInfoModel;
import api.RequestObject;
import api.ResponseObject;
import api.TransferEndpoint;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.List;

public class MoneyTransferTest {
    PaymentRemitInfoModel paymentRemitInfoModel;
    TransferEndpoint transferEndpoint;
    HashMap requestBody;
    SoftAssert softAssert;

    @DataProvider()
    public Object[][] dpCountriesAndLevels(){
        //Countries are received from payment-remit-info
        paymentRemitInfoModel = transferEndpoint.getPaymentRemitInfo(new HashMap());
        List<String> countries = paymentRemitInfoModel.getCountry();
        String[] serviceArr = {"1","2","3"};
        Object[][] returnCountries = new Object[countries.size()*serviceArr.length][2];
        int t = 0;
        for (int i = 0; i < serviceArr.length; i++) {
            if(t==countries.size()*serviceArr.length){
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


    ///The data for test is received by DataProvider
    @Test(dataProvider = "dpCountriesAndLevels")
    public void toBankAccount(String country,String serviceLevel){
        ///Setting request object( model)
        RequestObject requestObject = new RequestObject(
                country,
                "Account",
                "100",
                serviceLevel
        );
        requestObject.requestPrettyPrint();

        ///Receiving response model and setting it to response object
        ResponseObject responseObject = transferEndpoint.getRemittanceInfoForCountry(requestObject.getRequestModel());

        ///Assertions are done step by step, if any of steps is failed will be run the next test case
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
        ResponseObject responseObject = transferEndpoint.getRemittanceInfoForCountry(requestObject.getRequestModel());
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
        ResponseObject responseObject = transferEndpoint.getRemittanceInfoForCountry(requestObject.getRequestModel());
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

}
