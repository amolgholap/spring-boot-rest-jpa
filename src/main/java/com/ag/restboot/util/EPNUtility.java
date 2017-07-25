package com.ag.restboot.util;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;




/**
 * 
 * @author Amol N. Gholap-Predix2.0
 */
public class EPNUtility {
	private static final Log logger = LogFactory.getLog(EPNUtility.class);
	
	
	public static String checkNumber(String value) {
		String result = value;
		try {

			double dobleVal = 0;
			if (value.matches("-?\\d+(\\.\\d+)?")) {
				dobleVal = Double.parseDouble(value);

				if (dobleVal == Math.round(dobleVal)) {
					result = value;

				} else {
					DecimalFormat df = new DecimalFormat("#.## ");
					df.setRoundingMode(RoundingMode.FLOOR);
					result = df.format(Double.parseDouble(value));

				}
			}
		} catch (NumberFormatException e) {
			logger.error("NumberFormatException---" + e);
		} catch (Exception e) {
			logger.error("Exception----" + e);
		}
		return result;

	}

	
	
	public static boolean hasSpecialCharacter(String param1, String param2,
			String param3,String param4,String param5,String param6,String param7) {
		
		// String expression = "^[^((<|(>|(|(')|(%)|(^))]*$";
		// String expression = "^[^((<|(>|(|(')|(")|(!))]*$";
		logger.info("ENTER:PDMUtility:hasSpecialCharacter");
			
		logger.info("PDMUtility : hasSpecialCharacter  parameters : "+param1+" : "+param2+" : "+param3+" : "+param4+" : "+param5+" : "+param6+" : "+param7 );
		boolean isSpecial = false;
		String expression = "";//Define Expression;

		List<String> paramList = new ArrayList<String>();
		paramList.add(param1);
		paramList.add(param2);
		paramList.add(param3);
		paramList.add(param4);
		paramList.add(param5);
		paramList.add(param6);
		paramList.add(param7);
		
		

		for (String paramValue : paramList) {

			if (!isEmptyString(paramValue)) {
				CharSequence inputStr = paramValue.trim();
				Pattern pattern = Pattern.compile(expression,
						Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(inputStr);
				if (matcher.find()) {
					isSpecial = true;
				}
			}
			else
			{
				logger.info(paramValue+" is null or empty");
			}

		}
		logger.info("EXIT:PDMUtility:hasSpecialCharacter");
		
		return isSpecial;
	}
	
	public static boolean isEmptyString(String infoString) {
		boolean isEmptyString = false;
		if (null == infoString || "".equalsIgnoreCase(infoString)) {
			isEmptyString = true;
		}
		return isEmptyString;
	}
	public static String returnEmptyForString(String infoString) {
		String sString = "";
		if (!(null == infoString || "".equalsIgnoreCase(infoString))) {
			sString = infoString;
		}
		return sString;
	}
	public static String returnNullForString(String infoString) {
		String sString = null;
		if (!(null == infoString || "".equalsIgnoreCase(infoString))) {
			sString = infoString;
		}
		return sString;
	}
	public static String returnZeroForNullString(String infoString) {
		String sString = "0";
		if (!(null == infoString || "".equalsIgnoreCase(infoString)||"null".equalsIgnoreCase(infoString))) {
			sString = infoString;
		}
		return sString;
	}
	public static boolean isNumeric(String number){
		boolean isNumber=true;
		if ((null == number || "".equalsIgnoreCase(number)||"null".equalsIgnoreCase(number))) {
			isNumber=false;
		}
		try{
			Double.parseDouble(number);
		}catch(Exception e){
			isNumber=false;
		}
		return isNumber;
	}
	public static String roundPrecisionAsString(double value,int precision){
		String pre="#.";
		String sPre="";
		if(precision<=0){
			pre="#";
		}else{
			while(precision>0){
				sPre=sPre+"#";
				precision--;
			}
		}
		pre=pre+sPre;
		DecimalFormat df = new DecimalFormat(pre);
		double dValue =Double.valueOf(df.format(value));
		return String.valueOf(dValue);
	}
	public static String [] getArrayFromCommaSeparatedString(String sInputString){
		if(null==sInputString){
			return null;
		}
		String []arrInput=sInputString.split(",");
		return arrInput;
	}
	public static String getInBracesStringFromArray(String []arrInput){
		if(null==arrInput){
			return null;
		}
		int size=arrInput.length;
		String sOutput="";
		if(size==1){
			sOutput="("+arrInput[0]+")";
		}else{
			ArrayList<String> alString= new ArrayList<String>();
			for(int i=0;i<arrInput.length;i++){
				if(i==0){
					sOutput="("+arrInput[i]+"";
				}else{
					sOutput=sOutput+","+arrInput[i]+"";
				}
			}
			sOutput=sOutput+")";	
		}
		return sOutput;
	}

		public static double getAverageFromListOfString(ArrayList<String> al) {
			double sum = 0;
			double calcAverage = 0;
			if (null != al && al.size() > 0) {
				try {
					for (int i = 0; i < al.size(); i++)
						sum = sum + Double.parseDouble(al.get(i));
					calcAverage = sum / al.size();
				} catch (Exception e) {
					calcAverage = -1;
				}
			} else {
				calcAverage = -1;
			}
			return calcAverage;
	
		}
		public static double getMinFromList(ArrayList<Double> al) {
			try{
			if (null != al && al.size() > 0) {
				return Collections.min(al);
			}
			}catch(Exception e){
				return -1;
			}
			return -1;
		}
		public static double getMaxFromList(ArrayList<Double> al) {
			try{
			if (null != al && al.size() > 0) {
				return Collections.max(al);
			}
			}catch(Exception e){
				return -1;
			}
			return -1;
		}
		public static ArrayList<Double> GetValuesGreaterThan(ArrayList<Double> values, double limit, boolean orEqualTo)
		{
		    ArrayList<Double> modValues = new ArrayList<Double>();
		 
		    for (double value : values)
		        if (value > limit || (value == limit && orEqualTo))
		            modValues.add(value);
		 
		    return modValues;
		}
		 
		public static ArrayList<Double> GetValuesLessThan(ArrayList<Double> values, double limit, boolean orEqualTo)
		{
		    ArrayList<Double> modValues = new ArrayList<Double>();
		 
		    for (double value : values)
		        if (value < limit || (value == limit && orEqualTo))
		            modValues.add(value);
		 
		    return modValues;
		}
		
		public static String objectToJson(Object obj) {
			ObjectMapper mapper = new ObjectMapper();
			String jsonValue = "";

			try {

				jsonValue = mapper.writeValueAsString(obj);

			
			} catch (JsonGenerationException ex) {

			//logger.error("JsonGenerationException exception is :"+ex.getMessage());
			logger.error("JsonGenerationException exception is   " +ex.getLocalizedMessage(), ex);

			} catch (JsonMappingException ex) {

				//logger.error("JsonMappingException exception is :"+ex.getMessage());
				logger.error("JsonMappingException exception is   " +ex.getLocalizedMessage(), ex);

			} catch (IOException ex) {

				//logger.error("IOException exception is :"+ex.getMessage());
				logger.error("IOException exception is   " +ex.getLocalizedMessage(), ex);

			}
			return jsonValue;

		}
		public static Object jsonToObject(String jsonString,Object classObject) {
			ObjectMapper mapper = new ObjectMapper();

				try {
					classObject=mapper.readValue(jsonString,classObject.getClass());
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return classObject;
		}
		

		
}
