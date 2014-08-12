package singleton;

import java.util.LinkedList;

import android.app.Activity;

public class ActivityCollection {
	private static ActivityCollection activityCollection;
	private LinkedList<Activity> activitysList;
	
	private ActivityCollection(){
		if(this.getActivitysList()==null){
			this.setActivitysList(new LinkedList<Activity>());
		}
	}
	
	public static ActivityCollection getInstance(){
		if(activityCollection==null){
			activityCollection = new ActivityCollection();
		}
		return activityCollection;
	}

	public boolean addActivity(Activity a){
		if(a!=null){
			if(!this.getActivitysList().contains(a)){
				return this.getActivitysList().add(a);
			}else{
				return true;
			}
		}
		return false;
	}
	
	public void exit(){
		if(this.getActivitysList()!=null && this.getActivitysList().size()>0){
			for(Activity a : this.getActivitysList()){
				a.finish();
			}
		}
		System.exit(0);
	}
	
	public LinkedList<Activity> getActivitysList() {
		return activitysList;
	}

	public void setActivitysList(LinkedList<Activity> activitysList) {
		this.activitysList = activitysList;
	}
	
	
}
