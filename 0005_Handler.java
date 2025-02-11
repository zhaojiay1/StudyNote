android 什么时候用Handler?

  1、更新Ui:在Android中只有主线程可以更新ui.如果需要从后台更新UI，可以使用handler将任务切换到主线程执行。
	Handler handler = new Handler(Looper.getMainLooper());
	new Thread(new Runnable(){
		@Override
		public void run(){
			handler.post(new Runnable(){
                @Override
                public void run(){
                    //更新UI
                    textVidw.setText("refresh ui");
                }
			}
		}
	}).start();

2、定时任务:可以实现定时任务或延迟执行任务
Handler handler = new Handler();
handler.postDelayed(new Runnable(){
    @Override
    public void run(){
        //执行定时任务
        textVidw.setText("do delay task");
    }
},2000)//延迟2s执行

3、处理消息队列：Handler和Message一起使用，处理消息队列中的消息
Handler handler = new Handler(new Handler.Callback(){
    @Override
    public void handleMessage(Message msg){
        //处理消息
        switch(msg.what){
            case 1:
                textView.setText("handle message");
                break;
            case 2:
                textView.setText("handle message");
                break;
            default:
                break;
        }
    }
});

//发送消息
Message message = handler.obtainMessage(1);
handler.sendMessage(message);

4、线程间通信/处理耗时操作：子线程执行耗时任务，返回耗时任务数据给主线程处理。如果在主线程执行耗时操作可能导致应用超时无响应。
Handler handler =  new Handler();
new Thread(new Runable(){
    @Override
    public void run(){
        //执行后台耗时任务
        Message message = handler.obtainMessage(1);
        handelr.sendMessage(message);
    }
});

handler = new Handler(new Handler.Callback(){
    @Override
    public void handleMessage(Message msg){
        //处理消息
        if（msg.what == 1){
            //处理后台线程返回的数据
            String data = (String)msg.obj;
            textView.setText(data);
            return true;
        }
        return false;
    }
});

