package com.archystudio.sport;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class JGames implements Parcelable {
	public String AwayTeamFirst;
	public String AwayTeamLast;
	public int GameID;
	public String HomeTeamFirst;
	public String HomeTeamLast;
	public Date StartDate;
	
	public JGames(String date)
	{
		StartDate = DateUtil.parseDate(date);
	}
	
	private JGames(Parcel in) {
		this.AwayTeamFirst = in.readString();
		this.AwayTeamLast = in.readString();
		this.GameID = in.readInt();
		this.HomeTeamFirst = in.readString();
		this.HomeTeamLast = in.readString();
		this.StartDate = DateUtil.parseDate(in.readString());
	}
	 
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

		public JGames createFromParcel(Parcel in) {
			return new JGames(in);
		}

		public JGames[] newArray(int size) {
			return new JGames[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(this.AwayTeamFirst);
		arg0.writeString(this.AwayTeamLast);
		arg0.writeInt(this.GameID);
		arg0.writeString(this.HomeTeamFirst);
		arg0.writeString(this.HomeTeamLast);
		arg0.writeString(DateUtil.deparseDate(this.StartDate));
	}
}
