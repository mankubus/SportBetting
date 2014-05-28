package com.archystudio.sport;

import android.os.Parcel;
import android.os.Parcelable;

public class JOdds implements Parcelable{
	public String Bonuses;
	
	public int ML_Away;
	public int ML_Home;
	
	public String PlayersAccepted;
	
	public double SL_HomeLine;
	public int SL_HomeMoney;
	public double SL_AwayLine;
	public int SL_AwayMoney;
	
	public int SportBookID;
	public String SportBookURL;
	public String SportBookLogo;
	
	public int Total_AwayMoney;
	public int Total_HomeMoney;
	public double Total_TotalLine;
	
	public JOdds()
	{
		
	}
	
	private JOdds(Parcel in) {
		this.Bonuses = in.readString();
		
		this.ML_Away = in.readInt();
		this.ML_Home = in.readInt();
		
		this.PlayersAccepted = in.readString();
		
		this.SL_HomeLine = in.readDouble();
		this.SL_HomeMoney = in.readInt();
		this.SL_AwayLine = in.readDouble();
		this.SL_AwayMoney = in.readInt();
		
		this.SportBookID = in.readInt();
		this.SportBookURL = in.readString();
		this.SportBookLogo = in.readString();
		
		this.Total_AwayMoney = in.readInt();
		this.Total_HomeMoney = in.readInt();
		this.Total_TotalLine = in.readDouble();
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(this.Bonuses);
		
		arg0.writeInt(this.ML_Away);
		arg0.writeInt(this.ML_Home);
		
		arg0.writeString(this.PlayersAccepted);
		
		arg0.writeDouble(this.SL_HomeLine);
		arg0.writeInt(this.SL_HomeMoney);
		arg0.writeDouble(this.SL_AwayLine);
		arg0.writeInt(this.SL_AwayMoney);
		
		arg0.writeInt(this.SportBookID);
		arg0.writeString(this.SportBookURL);
		arg0.writeString(this.SportBookLogo);
		
		arg0.writeInt(this.Total_AwayMoney);
		arg0.writeInt(this.Total_HomeMoney);
		arg0.writeDouble(this.Total_TotalLine);	
	}
	
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

		public JOdds createFromParcel(Parcel in) {
			return new JOdds(in);
		}

		public JOdds[] newArray(int size) {
			return new JOdds[size];
		}
	};
}
