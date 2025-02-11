//1、非法参数
Public void setAge(int age){
	If(age<0 || age>150){
		Throw new IllegalArgumentException("age must be betweent 0-150");
	}
}
//2、空指针异常
Public void setName(String name){
	If(name==null){
		Throw new NullPointerException("name cannot be null");
	}
}
//3、资源异常:SQLException,IOException…
Public void readFile(String filePath) throws IOException{
	File file = new File(filePath);
	If(!file.exists()){
		Thorow new FileNotFoundException("file not found");
	}
}
//4、操作失败：当操作无法完成时，抛出OperationFailedException
public void processTransaction(Transaction transaction) throws OperationFailedException{
	if(!transaction.isValid()){
		throw new OperationFailedException("Transaction is invalid");
	}
}
//5、超出范围
当访问数组或集合时，索引超出范围，可以抛出IndexOutOfBoundsException
public int getElement(int[] array,int index){
	if(index<0 || index>=array.length){
		throw new IndexOutOfBoundsException("index must be between 0 and array.length");
	}
}
//6、不支持的操作：当某个操作不被支持时，可以抛出UnSupportedOperationException
public void remove(){
	throw new UnSupportedOperationException("remove operation is not supported");
}
//7、自定义义务逻辑异常
public class InsufficientFundException extends Exception{
	public InsufficientFundException(String msg){
		/**
		 *显示调用父类构造函数，确保父类属性被正确初始化，
		 *且super必须是子类构造函数的第一条语句，确保父类先于子类被初始化
		 */	
		super(msg);
	}	
}
public void withdraw(double amount) throws InsufficientFundException{
	if(amount>balance){
		throw new InsufficientFundException("Insufficient funds for withdrawal");
	}
	balance -= amount;
}
