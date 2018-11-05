package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIPServiceTests {

    @Test

    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap().getGeoIP("195.208.131.vvv");
                //("195.208.131.1");
        assertEquals(geoIP.getCountryCode(), "RUS");


    }
}
