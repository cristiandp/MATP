package com.example.cristian.mamaandroidthermalpos.Bluetooth;

import com.example.cristian.mamaandroidthermalpos.Productos.Producto;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.lang.Byte;
public class ESCUtil {
    static Calendar c = Calendar.getInstance();

	public ESCUtil(){

    }

	public static final byte ESC = 27;// Escape
	public static final byte FS = 28;// Text delimiter
	public static final byte GS = 29;// Group separator  
	public static final byte DLE = 16;// data link escape
	public static final byte EOT = 4;// End of transmission
	public static final byte ENQ = 5;// Enquiry character
	public static final byte SP = 32;// Spaces
	public static final byte HT = 9;// Horizontal list
	public static final byte LF = 10;//Print and wrap (horizontal orientation)
	public static final byte CR = 13;// Home key
	public static final byte FF = 12;// Carriage control (print and return to the standard mode (in page mode))
	public static final byte CAN = 24;// Canceled (cancel print data in page mode)

	// ------------------------Initialize the printer-----------------------------

	/**
	 * Initialize the printer
	 * @return
	 */
	public static byte[] init_printer() {
		byte[] result = new byte[2];
		result[0] = ESC;
		result[1] = 64;
		return result;
	}

	// ------------------------Wrap-----------------------------

	/**
	 * Wrap
	 * 
	 * @param lineNum  how many line do you want wrap
	 * @return
	 */
	public static byte[] nextLine(int lineNum) {
		byte[] result = new byte[lineNum];
		for (int i = 0; i < lineNum; i++) {
			result[i] = LF;
		}

		return result;
	}

	// ------------------------underline-----------------------------

	/**
	 * draw a underline（1 pixel width）
	 * 
	 * @return
	 */
	public static byte[] underlineWithOneDotWidthOn() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 45;
		result[2] = 1;
		return result;
	}

	/**
	 * draw a underline（2 pixel width）
	 * 
	 * @return
	 */
	public static byte[] underlineWithTwoDotWidthOn() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 45;
		result[2] = 2;
		return result;
	}

	/**
	 * cancel draw a underline
	 * 
	 * @return
	 */
	public static byte[] underlineOff() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 45;
		result[2] = 0;
		return result;
	}

	// ------------------------bold-----------------------------

	/**
	 * select bold option
	 * 
	 * @return
	 */
	public static byte[] boldOn() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 69;
		result[2] = 0xF;
		return result;
	}

	/**
	 * cancel bold option
	 * 
	 * @return
	 */
	public static byte[] boldOff() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 69;
		result[2] = 0;
		return result;
	}

	// ------------------------Align-----------------------------

	/**
	 * Align left
	 * 
	 * @return
	 */
	public static byte[] alignLeft() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 97;
		result[2] = 0;
		return result;
	}

	/**
	 * 居中对齐
	 * 
	 * @return
	 */
	public static byte[] alignCenter() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 97;
		result[2] = 1;
		return result;
	}

	/**
	 * Align right
	 * 
	 * @return
	 */
	public static byte[] alignRight() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 97;
		result[2] = 2;
		return result;
	}

	/**
	 * Horizontal move col columns to the right
	 * 
	 * @param col
	 * @return
	 */
	public static byte[] set_HT_position(byte col) {
		byte[] result = new byte[4];
		result[0] = ESC;
		result[1] = 68;
		result[2] = col;
		result[3] = 0;
		return result;
	}
	// ------------------------Font bigger-----------------------------

	/**
	 * Font bigger 5 times than normal
	 * 
	 * @param num
	 * @return
	 */
	public static byte[] fontSizeSetBig(int num) {
		byte realSize = 0;
		switch (num) {
		case 1:
			realSize = 0;
			break;
		case 2:
			realSize = 17;
			break;
		case 3:
			realSize = 34;
			break;
		case 4:
			realSize = 51;
			break;
		case 5:
			realSize = 68;
			break;
		case 6:
			realSize = 85;
			break;
		case 7:
			realSize = 102;
			break;
		case 8:
			realSize = 119;
			break;
		}
		byte[] result = new byte[3];
		result[0] = 29;
		result[1] = 33;
		result[2] = realSize;
		return result;
	}

	// ------------------------Font smaller-----------------------------

	/**
	 * font smaller
	 * 
	 * @param num
	 * @return
	 */
	public static byte[] fontSizeSetSmall(int num) {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 33;

		return result;
	}

	// ------------------------Paper cutting-----------------------------

	/**
	 * Paper cutting
	 * 
	 * @return
	 */
	public static byte[] feedPaperCutAll() {
		byte[] result = new byte[4];
		result[0] = GS;
		result[1] = 86;
		result[2] = 65;
		result[3] = 0;
		return result;
	}

	/**
	 * Paper cutting（the left leave some）
	 * 
	 * @return
	 */
	public static byte[] feedPaperCutPartial() {
		byte[] result = new byte[4];
		result[0] = GS;
		result[1] = 86;
		result[2] = 66;
		result[3] = 0;
		return result;
	}

	// ------------------------Cutting paper-----------------------------
	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}

	public static byte[] byteMerger(byte[][] byteList) {

		int length = 0;
		for (int i = 0; i < byteList.length; i++) {
			length += byteList[i].length;
		}
		byte[] result = new byte[length];

		int index = 0;
		for (int i = 0; i < byteList.length; i++) {
			byte[] nowByte = byteList[i];
			for (int k = 0; k < byteList[i].length; k++) {
				result[index] = nowByte[k];
				index++;
			}
		}
		for (int i = 0; i < index; i++) {
			// CommonUtils.LogWuwei("", "result[" + i + "] is " + result[i]);
		}
		return result;
	}

    public static byte[] generarTicket(List<Producto> productos){
		int tMax = 32;
		byte[] res = getHeader();
		byte[] m;
		String separacion = "";
		try {
			m = " EUR".getBytes("Cp858");
			for (int i = 0; i < productos.size();i++){
				byte[] nombre= productos.get(i).getNombre_producto().getBytes("Cp858");
				String precioTotal = String.format(Locale.getDefault(),"%.2f", productos.get(i).getPrecio() * productos.get(i).getCantidad());
				int caracteres = nombre.length+precioTotal.length()+"EUR".length();

				for(int j = 0; j < tMax-caracteres-1;j++){
					separacion +=".";
				}

				byte[] precio= precioTotal.getBytes("Cp858") ;
				byte[] bSeparacion = separacion.getBytes();
				byte[][] unProducto = new byte[][]{res,nombre,bSeparacion,precio,m,alignLeft(),nextLine(1)};
				res = byteMerger(unProducto);
				separacion = "";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		res = byteMerger(res,nextLine(3));
		return res;
	}

	public static byte[] generarTicket2(List<Producto> productos){
		StringBuffer strBuff = new StringBuffer();

		for (int i = 0 ; i < productos.size();i++){
			strBuff.append(productos.get(i).getNombre_producto());
			strBuff.append(String.format(Locale.getDefault(),"%.2f",productos.get(i).getPrecio()));
			strBuff.append("\n");
		}
		strBuff.append("\n\n\n");
	    return  byteMerger(getHeader(),strBuff.toString().getBytes());
	}

    public static byte[] getHeader(){
        byte[] separadorAsteriscos = "********************************".getBytes();
        byte[] logoText = "MamaAndroid".getBytes();

        String sec = Integer.toString(c.get(Calendar.SECOND));
        String min = Integer.toString(c.get(Calendar.MINUTE));
        String hor = Integer.toString(c.get(Calendar.HOUR));

        String dia = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(c.get(Calendar.MONTH)+1);
        String ano = Integer.toString(c.get(Calendar.YEAR));

        byte[] fecha = (hor+":"+min+"  "+dia+"/"+mes+"/"+ano).getBytes();

        byte[][] merge = new byte[][]{
				separadorAsteriscos,nextLine(2),
                alignCenter(),boldOn(),logoText,boldOff(),nextLine(1),
                fecha,nextLine(2),
                separadorAsteriscos,separadorAsteriscos,nextLine(3),
                alignLeft()};

        return byteMerger(merge);
    }

	public static byte[] getFooter(){
		byte[] height = new byte[]{-100};
		byte[] gs = new byte[]{GS};
		byte[] esc = new byte[]{ESC};
		byte[] h = new byte[]{104};
		byte[] k = new byte[]{107};
		byte[] enc = new byte[]{4};
		byte[] numero ="454654586".getBytes();
		byte[] nul = new byte[]{0};

		byte[][] toMerg = new byte[][]{gs,h,height,esc,k,enc,numero,nul};


		return byteMerger(toMerg);
	}


}