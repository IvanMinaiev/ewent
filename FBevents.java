package com.iam.ewent;

/**
 * Created by tomato on 07.04.17.
 */

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import facebook4j.*;
import facebook4j.Event;
import facebook4j.Facebook;
import facebook4j.auth.AccessToken;
import facebook4j.ResponseList;
import com.restfb.Version;
import static org.apache.commons.lang.StringEscapeUtils.unescapeJava;

public class FBevents {
       static public String access = "EAACtokZC5uRIBAMs7RCVwZBfa1MhVDagxaXmiXCgd0wq6vWa1sC1V5szvTZB0G2vWm5zfDqWCPMCBA4diVWZA7GVS0Jj7nDGKAoeAFeXrkfZBfBKUwcxvAeMHqiwBVGgUWzjwSA4YR2dKO2sMBOJRRPfmxgCJMXwZD";


    public static void addFBevents(String city) throws Exception {

        Facebook client = new FacebookFactory().getInstance();
        AccessToken at = new AccessToken(access);

        client.setOAuthAppId("190912638073106", "1f958b1e5558fb47cbbf23814ec71616");
        client.setOAuthAccessToken(at);

        ResponseList<Event> eventResponseList = client.searchEvents(city);

        FacebookClient fbClient = new DefaultFacebookClient(access, Version.VERSION_2_8);

        for(Event event : eventResponseList) {
        com.restfb.types.Event ev1 = fbClient.fetchObject( event.getId(), com.restfb.types.Event.class);

        com.restfb.types.Place place = ev1.getPlace();
        String a = place.getLocationAsString();

        Requester req = new Requester(ev1.getId());


            Ewent ewent = new Ewent();
            ewent.setName(ev1.getName());
            ewent.setMembersCount(ev1.getAttendingCount());
            ewent.setCover(new JsonLocation(req.reqest()).cover());
            ewent.setCity(new JsonLocation(unescapeJava(a)).city());
            ewent.setDescription(ev1.getDescription());
            ewent.setStartDate(ev1.getStartTime());

            //ewent.setCoordinates(new JsonLocation(unescapeJava(a)).coord()); Есть точные координаты, можно добавить поле
            //ewent.setAddress(new JsonLocation(unescapeJava(a)).address()); Есть точный адрес. Очень пригодится если карта будет

            ewentList.add(ewent);


        }
    }
}