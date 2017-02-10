/**
 * 
 */
package ar.com.adriabe.model.constant;

/**
 * @author Mildo
 *
 */
public class EurekaConstants{

/*Common Constants*/
    /* SimpleDateFormat Patterns*/
    public static final String PATTERN_READABLE = "dd/MM/yyyy kk:mm:ss";
    public static final String PATTERN_READABLE_SHORT = "dd/MM/yyyy";
    public static final boolean DEBUG = false;
    public static final String SUCCESS = "SUCCESS";

/*Business Constants*/
    public static final int CLIENT_TYPE_BLACK= 0;
    public static final int CLIENT_TYPE_BLACK_AND_WHITE = 1;
    public static final int CLIENT_TYPE_WHITE = 2;



    /* Common utilities for MYSQL*/
	public static final String DATE_FORMAT_MYSQL = "yyyy-MM-dd";
	/* SimpleDateFormat Patterns*/
	public static final String PATTERN_DDMMAA = "ddMMyy";
	public static final String PATTERN_DAY_AND_DATE = "EEEE dd";
	public static final String PATTERN_DAY = "EEEE";
	public static final String PATTERN_MONTH = "MMMMM";
	public static final String PATTERN_MONTH_AND_YEAR = "MMMMM - yy";
//	public static final String PATTERN_READABLE = "dd/MM/yyyy kk:mm:ss";
//    public static final String PATTERN_READABLE_SHORT = "dd/MM/yyyy";
	public static final String PATTERN_TIME = "kk:mm:ss";

	public static final String PATTERN_EXCEL = "dd_MM_yy";
	/* Allawed Users domains*/
	public static final String[] COMPANIES_DOMAINS = {"adriabe.com.ar","yabitel.com.ar","iland.com.ar","burnaby.com.ar","gmail.com"};

	/*Common Error codes*/
	public static final int ORDER_NOT_FOUND = 1;
	public static final int INVALID_DATA = 2;
	public static final int GENERAL_ERROR = -1;
	
	public static final String GENERAL_ERROR_MESSAGE="Ha ocurrido un error, la operación no pudo realizarse.";
	public static final String OPERATION_FAILED_ERROR_MESSAGE="La operación no se pudo concretar";
	public static final String INVALID_DATA_ERROR_MESSAGE="Los datos no son validos";

	public static final String CHEQUE_RECHAZADO_MSG = "Cheque rechazado";
	public static final String GASTOS_ADMINISTRATIVOS_MSG = "Gastos administrativos";


	
	public static final String NUMERO_FACTURA_A = "numero-factura-A";
	public static final String NUMERO_FACTURA_B = "numero-factura-B";
	public static final String NUMERO_REMITO_A = "numero-remito-A";
	public static final String NUMERO_REMITO_B = "numero-remito-B";
	public static final String NUMERO_NOTA_CREDITO = "numero-nota-credito";
	public static final String NUMERO_NOTA_DEBITO = "numero-nota-debito";
	public static final String NUMERO_RECIBO = "numero-recibo";
	
	public static final String CHEQUE_CODIGO_LBN = "cheque-codigo-lbn";
	public static final String CHEQUE_CODIGO_SJR = "cheque-codigo-sjr";
	public static final String CHEQUE_PREFIJO_SJR = "SJR";
	public static final String CHEQUE_PREFIJO_LBN = "LBN";
	
	public static final String NUMERO_ROLLO = "numero-rollo";
	
	public static final String NUMERO_ORDEN_PAGO = "numero-orden-pago";
	
	public static final double IVA_PERCENT_3 = 3.0;
	public static final double IVA_PERCENT_10_5 = 10.5;
	public static final double IVA_PERCENT_21 = 21.0;
	public static final double IVA_PERCENT_27  = 27.0;
	public static final Object IVA_PERCENT_ING_BRUTOS = 0;
	
	
	
	public static final String PAYMENT = "Su Pago";
	public static final String SUPPLIER_PAYMENT = "Se Pago";
	public static final String BILL_CONCEPT = "Factura Nº";
	
	public static final String LISTA_GENERAL_STR = "Lista General";


    public static final String FILE_PATH = "webapps/adriabe/listing/excels";

	

}

