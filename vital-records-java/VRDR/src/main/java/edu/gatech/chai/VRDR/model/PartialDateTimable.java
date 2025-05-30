package edu.gatech.chai.VRDR.model;

import edu.gatech.chai.VRDR.model.util.CommonUtil;
import org.hl7.fhir.r4.model.*;

public interface PartialDateTimable {
    DeathDate deathDate = new DeathDate();
    InjuryIncident injuryIncident = new InjuryIncident();
    default void addPartialDateExtension(String event, IntegerType year, String yearDataAbsentReason, IntegerType month, String monthDataAbsentReason,
                                         IntegerType day, String dayDataAbsentReason) {
        Extension baseExtension = addPartialDateBaseExtension();
        addPartialDateYear(baseExtension,year,yearDataAbsentReason);
        addPartialDateMonth(baseExtension,month,monthDataAbsentReason);
        addPartialDateDay(baseExtension,day,dayDataAbsentReason);
        initEventDate(event, baseExtension);
    }

    default void addPartialDateTimeExtension(String event, IntegerType year, String yearDataAbsentReason, IntegerType month, String monthDataAbsentReason,
                                             IntegerType day, String dayDataAbsentReason, StringType time, String timeDataAbsentReason) {
        Extension baseExtension = addPartialDateBaseExtension();
        addPartialDateYear(baseExtension,year,yearDataAbsentReason);
        addPartialDateMonth(baseExtension,month,monthDataAbsentReason);
        addPartialDateDay(baseExtension,day,dayDataAbsentReason);
        addPartialDateTime(baseExtension,time,timeDataAbsentReason);
        initEventDate(event, baseExtension);
    }

    static void initEventDate(String event, Extension baseExtension) {
        if(event.equals("death")) {
            deathDate.setValue(new DateTimeType());
            deathDate.getValue().addExtension(baseExtension);
        }
        else if(event.equals("injury")) {
            injuryIncident.setValue(new DateTimeType());
            injuryIncident.getValue().addExtension(baseExtension);
        }
    }

    default Extension addPartialDateBaseExtension() {
        Extension baseExtension = new Extension(CommonUtil.partialDatePartAbsentReasonURL);
        return baseExtension;
    }

    default void addPartialDateYear(Extension baseExtension, IntegerType year,String dataAbsentReason) {
        if(dataAbsentReason != null && !dataAbsentReason.isEmpty()) {
            baseExtension.addExtension(new Extension(CommonUtil.partialDateDateYearAbsentReasonURL,CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet)));
        }
        else if(year != null && !year.isEmpty()){
            baseExtension.addExtension(new Extension(CommonUtil.partialDateDateYearURL,year));
        }
    }

    default void addPartialDateMonth(Extension baseExtension, IntegerType month,String dataAbsentReason) {
        if(dataAbsentReason != null && !dataAbsentReason.isEmpty()) {
            baseExtension.addExtension(new Extension(CommonUtil.partialDateDateMonthAbsentReasonURL,CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet)));
        }
        else if(month != null && !month.isEmpty()){
            baseExtension.addExtension(new Extension(CommonUtil.partialDateDateMonthURL,month));
        }
    }

    default void addPartialDateDay(Extension baseExtension, IntegerType day,String dataAbsentReason) {
        if(dataAbsentReason != null && !dataAbsentReason.isEmpty()) {
            baseExtension.addExtension(new Extension(CommonUtil.partialDateDateDayAbsentReasonURL,CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet)));
        }
        else if(day != null && !day.isEmpty()){
            baseExtension.addExtension(new Extension(CommonUtil.partialDateDateDayURL,day));
        }
    }

    default void addPartialDateTime(Extension baseExtension, StringType time,String dataAbsentReason) {
        if(dataAbsentReason != null && !dataAbsentReason.isEmpty()) {
            baseExtension.addExtension(new Extension(CommonUtil.partialDateDateTimeAbsentReasonURL,CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet)));
        }
        else if(time != null && !time.isEmpty()){
            baseExtension.addExtension(new Extension(CommonUtil.partialDateDateTimeURL,time));
        }
    }
}
