package kjf.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import kjf.ops.ReportDAO;
import kjf.ops.ReportEntity;
/**
 * ������ �� ��� ó�� �ʿ�...
 * <p>Subsystem		:  </p>
 * <p>Title			: Excel ��ƿ�Լ� </p>
 * <p>Description	: Excel ��ƿ�Լ��� �����Ͽ� ����. </p>
 * <p>���� TABLE		: </p>
 * @author ����
 * @version 1.0	2003.05.29 <br/>
 */


public class KJFExcel {

	private POIFSFileSystem fs;
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;

	private int startRow = 0;
	private int lastRow = 0;
	
	private int lastCell = 0;//2007-2-3 ����� ����

	/**
	 *  ������
	 *
	 *@param  source                     Description of the Parameter
	 *@exception  FileNotFoundException  Description of the Exception
	 *@exception  IOException            Description of the Exception
	 */
	public KJFExcel(InputStream source)
		throws FileNotFoundException, IOException {
			
		fs = new POIFSFileSystem(source);
		// �о���� Excel������ �ٷ�� ���� workbook instance�� ������Ų��.
		workbook = new HSSFWorkbook(fs);
		
	}


	/**
	 *  ���ۿ��� �����Ѵ�.
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow - 1;
	}

	
	/**
	 *  ���ۿ��� �����Ѵ�.
	 */
	public int getStartRow() {
		return startRow;
	}
	
	/**
	 *  Merged Cell ���θ� �˻��Ѵ�.
	 *
	 *@param  row     Description of the Parameter
	 *@param  column  Description of the Parameter
	 *@return  boolean The mergedRegions value
	 */
	private boolean isMergedRegions(int row, int column) {
		int numRegions = 0;
		boolean result = false;
		
		try {
			numRegions = sheet.getNumMergedRegions();
			for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
				Region region = sheet.getMergedRegionAt(i);
				if (region.contains(row, (short) column)) {
					result = true;
					break;
				}
			}
		} catch (Exception e) {}
		
		return result;
	}


	/**
	 *  ���������� ���� ����� 2���� �迭�� �����Ѵ�.
	 * 
	 *@return  String[][]
	 */
	public String[][] read() throws Exception {

		// ������ workbook���κ��� sheet�� �о� ���δ�.
		sheet = workbook.getSheetAt(0);
		lastRow = sheet.getLastRowNum();
		startRow = getStartRow();
		
		String[][] result = new String[lastRow -startRow+1][]; 
		
		int thisCellNum = 0;
				
		for (int i = startRow; i <= lastRow; i++) {

			HSSFRow row = sheet.getRow(i);
			
			if(lastCell>0){
				thisCellNum = lastCell;				
			}else{
				thisCellNum = row.getLastCellNum();				
			}
			
			result[i-startRow] = new String[thisCellNum];
			
			for (int j = row.getFirstCellNum(); j < thisCellNum; j++) {
				HSSFCell cell = row.getCell((short) j);
				
				int cellType = HSSFCell.CELL_TYPE_BLANK;
				
				if (cell != null) {
					cellType = cell.getCellType();					
				}
				
				// cell Ÿ�Կ� ���� cell�� ���� ��½�Ų��				
				switch (cellType) {				
				case HSSFCell.CELL_TYPE_NUMERIC:		// 0
					double d = cell.getNumericCellValue();
					// ����Ʈ Ÿ�Կ��θ� üũ�Ѵ�.
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						// format in form of YYYYMMDD
						SimpleDateFormat formatter =
							new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.KOREA);
						String cellText = formatter.format(HSSFDateUtil.getJavaDate(d));
						result[i-startRow][j] = cellText;
					} else {
						result[i-startRow][j] = String.valueOf(cell.getNumericCellValue());
					}
					break;
				case HSSFCell.CELL_TYPE_STRING:			// 1
					//System.out.println(i+"="+startRow+"="+j+"="+cell.getStringCellValue());
					result[i-startRow][j] = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_FORMULA:		// 2
					result[i-startRow][j] = cell.getCellFormula();
					break;
				case HSSFCell.CELL_TYPE_BLANK:			// 3
					if( i-startRow >0 && isMergedRegions(i,j)) {
						result[i-startRow][j] = result[i-startRow-1][j];
						//System.out.println(i+"="+j+"="+result[i-startRow][j]);
					} else {						
						//result[i-startRow][j] = "";
					}
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:		// 4
					result[i-startRow][j] = String.valueOf(cell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_ERROR:			// 5
					result[i-startRow][j] = String.valueOf(cell.getErrorCellValue());
					break;
				default:
					break;
				}
			}
		}

 
		return result;
	}
	
	public void setLastCell(int lastCell){//	2007-2-3 ����� ����
		this.lastCell = lastCell;
	}
	

}