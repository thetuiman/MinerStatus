package me.davidgreene.minerstatus.util;

import me.davidgreene.minerstatus.beans.BitpoolStatus;
import me.davidgreene.minerstatus.beans.BtcMine;
import me.davidgreene.minerstatus.beans.DeepbitStatus;
import me.davidgreene.minerstatus.beans.SlushStatus;
import me.davidgreene.minerstatus.beans.Status;

import com.google.gson.Gson;

public class StatusObjectFactory {
	
	public static Status getStatusObject(String result, String identifier){
		if (identifier == null){
			return null;
		}
		Gson gson = new Gson();
		if (identifier.equals("bitcoinpool")){
			return gson.fromJson(result, BitpoolStatus.class);
		} else if (identifier.equals("slush")){
			return gson.fromJson(result, SlushStatus.class);
		} else if (identifier.equals("deepbit")){
			return gson.fromJson(result, DeepbitStatus.class);
		} else if (identifier.equals("btcmine")){
			return gson.fromJson(result, BtcMine.class);
		}
		return null;
	}
}
