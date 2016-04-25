package com.app.usecase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternExtractor 
{
	public static void main(String[] args) {
		Pattern pt= Pattern.compile("deviceInventoryList\":\"([^+]*)\",\"parameters\"");
		String regExp="\"id\":\"14\",\"deviceTypeId\":14,\"name\":\"Centralised Air Condtioning\",\"category\":\"Device\",\"model\":\"21\",\"make\":\"2016\",\"manufacturer\":\"Voltas\",\"supportedProtocol\":\"Zigbee\",\"status\":\"Active\",\"deviceTypeUniqueId\":3,\"interfaceId\":1,\"deviceInventoryList\":\"\",\"parameters\":[{\"id\":\"15\",\"deviceTypePropertyId\":\"15\",\"parameter\":\"Ac\",\"threshold\":\"20\",\"unit\":\"Temp\"}],\"inventoryAssociated\":false,\"uidevicetypeId\":\"div_14\",\"rowdevicetypeId\":\"row_14";
		String regExp2="\"id\":\"14\",\"deviceTypeId\":14,\"name\":\"Centralised Air Condtioning\",\"category\":\"Device\",\"model\":\"21\",\"make\":\"2016\",\"manufacturer\":\"Voltas\",\"supportedProtocol\":\"Zigbee\",\"status\":\"Active\",\"deviceTypeUniqueId\":3,\"interfaceId\":1,\"deviceInventoryList\":\"DEvice1,Device_2\",\"parameters\":[{\"id\":\"15\",\"deviceTypePropertyId\":\"15\",\"parameter\":\"Ac\",\"threshold\":\"20\",\"unit\":\"Temp\"}],\"inventoryAssociated\":false,\"uidevicetypeId\":\"div_14\",\"rowdevicetypeId\":\"row_14";
		Matcher m=pt.matcher(regExp);
		int deviceCount=0;
		//System.out.println(m.find());
		while(m.find())
		{
			System.out.println("==>"+m.group(1));
		}
	}

}
