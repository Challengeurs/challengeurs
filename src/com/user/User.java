package com.user;

import java.sql.Date;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.facebook.internal.Logger;
import com.facebook.model.GraphUser;

public class User implements Parcelable{
	private static final String TAG = "USER";
	String firstname;
	String lastname;
	String FbId;
	Date birthday;
	boolean isFb;
	
	public User(String name){
		this.firstname = name;
		this.
		isFb = false;
	}
	

	public User(GraphUser user) {
		this.firstname = user.getFirstName();
		this.lastname = user.getLastName();
		this.FbId = user.getId();
		isFb = true;
		try{
		this.birthday = Date.valueOf(user.getBirthday());
		}catch(Exception e){
			Log.e(TAG,"impossible de récuperer la date");
		}
	}
	public int describeContents()
	{
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeString(firstname);
		dest.writeString(lastname);
		if(isFb)
			dest.writeString(FbId);
		else
			dest.writeString("NOT_FB");
		if(birthday != null)
			dest.writeString(birthday.toString());
		else
			dest.writeString("NO_BIRTHDAY");
	}

	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>()
			{
			    @Override
			    public User createFromParcel(Parcel source)
			    {
			        return new User(source);
			    }

			    @Override
			    public User[] newArray(int size)
			    {
				return new User[size];
			    }
			};

			public User(Parcel in) {
				this.firstname = in.readString();
				this.lastname = in.readString();
				this.FbId = in.readString();
				if(this.FbId.equals("NOT_FB")){
					isFb = false;
					this.FbId = null;
				}else{
					isFb = true;
				}
				String date = in.readString();
				if(date.equals("NO_BIRTHDAY"))
					this.birthday = null;
				else
					try{
						this.birthday = Date.valueOf(date);
					}catch(Exception e){
						Log.e(TAG,"impossible de récuperer la date");
						this.birthday = null;
					}

			}
}
