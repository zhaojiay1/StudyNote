public class tipReceiver extends BroadcastReceiver{
	private static final String TAG = "tipReceiver";
	private Context mContext;
	private static Toast mToast = null;//static 全局状态

	@Override
	public void onReceive(Context context,Intent intent){

		String action = intent.getAction();
		mContext = context;
	
		if(action==null){
			return;
		}

		Log.i(TAG,"action = " + action);
		//这个action是谁给的  ？ 应该是mouse厂商
		if(("android.intent.action.xxx").equals(action)){
			openNotifyMessgae(mContext.getResources().getString(R.string.xxx));
		}else if(("android.intent.action.xxx").equals(action)){
			openNotifyMessage(mContext.getResoureces().getString(R.string.xxx));
		}
	}

	private void openNotifyMessgae(final String msg){
		/**
		 *使用handler调用showToast
		 *好处是 确保showToast在ui线程中执行，防止出现异常

		 *1、looper用于管理线程的消息队列
		 *2、将looper.getMainLooper对象传给handler,使得创建的handler线程和主线程关联
		 *3、handler.post(new Runnable())将一个Runable对象加入到主线程中，当执行Runnable中的
		 *run方法时，会在主线程中被执行，如ui
		 */
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable(){
			public void run(){
				showToast(msg);
			}
		});
	}

	private void showToast(final String msg){
		if(mToast==null){
			mToast = Toast.makeText(mContext,msg,Toast.LENGTH_SHORT);
			mToast.show();
		}else{
			//防止重复弹出toast
			mToast.cancel();
		}
	}
}
