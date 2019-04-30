package com.sensiple.qa.util;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.sensiple.qa.datadriver.CaseStep;

public class ExcelUtils {
	public static String orName;
	public static String orLocatorStart;
	public static String orLocatorEnd;
	public static String ModuleName;

	public List<CaseStep> readTestCase(File readExcel, File orReadExcel) throws InvalidFormatException, IOException {
		Workbook workbook = WorkbookFactory.create(readExcel);
		List<CaseStep> testCaseSteps = new LinkedList<CaseStep>();
		Sheet sheet = workbook.getSheetAt(0);

		Workbook orWorkBook = WorkbookFactory.create(orReadExcel);
		Sheet orSheet = orWorkBook.getSheetAt(0);
		Map<String, String> orMap = readORMap(orSheet);

		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext()) {
			Row row = rows.next();

			if (row.getRowNum() == 0) {
				continue;
			}

			CaseStep eachStep = new CaseStep();

			if (row.getCell(0) != null) {
				eachStep.setStepNo((int) row.getCell(0).getNumericCellValue());
			}

			if (row.getCell(0) != null) {
				eachStep.setStepNo((int) row.getCell(0).getNumericCellValue());
			}

			if (row.getCell(1) != null) {
				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				if (row.getCell(1).getStringCellValue().isEmpty()) {
					//
				} else {
					eachStep.setAction(StringUtils.trim(row.getCell(1).getStringCellValue()));
				}
			}
			if (row.getCell(2) != null) {
				row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
				eachStep.setLocateBy((StringUtils.trim(row.getCell(2).getStringCellValue())));
			}

			if (row.getCell(3) != null) {
				row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
				orName = (StringUtils.trim(row.getCell(3).getStringCellValue()));
				if (row.getCell(3).getStringCellValue().isEmpty()) {
					//
				} else if (orName.contains(",")) {
					String[] orNameSplit = orName.split(",");
					orLocatorStart = orMap.get(orNameSplit[0]);
					orLocatorEnd = orMap.get(orNameSplit[1]);
					eachStep.setOrLocatorStart(orLocatorStart);
					eachStep.setOrLocatorEnd(orLocatorEnd);
					eachStep.setOrLocator(orLocatorStart + orLocatorEnd);
				} else {
					String orLocator = orMap.get(StringUtils.trim(row.getCell(3).getStringCellValue()));
					eachStep.setOrLocator(orLocator);
				}
			}
			if (row.getCell(4) != null) {
				row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
				if (row.getCell(4).getStringCellValue().isEmpty()) {
					//
				} else {
					eachStep.setInputData(StringUtils.trim(row.getCell(4).getStringCellValue()));
				}
			}
			if (row.getCell(5) != null) {
				row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
				eachStep.setDescription(StringUtils.trim(row.getCell(5).getStringCellValue()));
			}
			if (row.getCell(6) != null) {
				row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
				eachStep.setExpectedResult(StringUtils.trim(row.getCell(6).getStringCellValue()));
			}
			if (row.getCell(7) != null) {
				row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
				if (row.getCell(7).getStringCellValue().isEmpty()) {
					//
				} else {
					eachStep.setReferenceStep(StringUtils.trim(row.getCell(7).getStringCellValue()));
				}
			}
			testCaseSteps.add(eachStep);
		}
		return testCaseSteps;
	}

	private Map<String, String> readORMap(Sheet orSheet) {
		Map<String, String> orMap = new HashMap<String, String>();
		Iterator<Row> rows = orSheet.rowIterator();

		while (rows.hasNext()) {
			Row row = rows.next();

			if (row.getRowNum() == 0) {
				continue;
			}
			if (row.getCell(0) != null && row.getCell(1) != null) {
				row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(1).getStringCellValue();
				orMap.put(StringUtils.trim(row.getCell(0).getStringCellValue()), StringUtils.trim(row.getCell(1).getStringCellValue()));
			}
		}
		return orMap;
	}

	public Collection<File> readTestCaseFiles(String folderName) {
		File testCaseFolder = new File(folderName);
		File[] matchingFiles = testCaseFolder.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.startsWith("TestCaseExeSheet");
			}
		});

		final Collection<File> testCaseList = new LinkedList<File>();
		if (matchingFiles.length == 1) {
			final File testCaseSheet = matchingFiles[0];
			if (testCaseSheet.getName().endsWith(".csv") || testCaseSheet.getName().endsWith(".CSV")) {
				try {
					readTestsFromCsvFile(folderName, testCaseSheet, testCaseList);
				} catch (final Throwable ex) {
					throw new RuntimeException(ex);
				}
			} else {
				Workbook workbook = null;
				try {
					workbook = WorkbookFactory.create(testCaseSheet);
				} catch (InvalidFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rows = sheet.rowIterator();
				while (rows.hasNext()) {
					Row row = rows.next();

					if (row.getRowNum() == 0) {
						// skip header row
						continue;
					}
					if ((row.getCell(1) == null || row.getCell(1).toString().trim().isEmpty()) && row.getCell(0) != null) {
						row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						String moduleName = row.getCell(0).getStringCellValue();
						ModuleName = moduleName = cleanModuleName(moduleName);
						File moduleFolder = new File(folderName + "/" + moduleName);
						final String[] extensionList = {"xlsx", "xls"};
						testCaseList.addAll(FileUtils.listFiles(moduleFolder, extensionList, true));
					} else if ((row.getCell(1) != null) && row.getCell(0) != null) {
						row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
						String moduleName = row.getCell(0).getStringCellValue();
						ModuleName = moduleName = cleanModuleName(moduleName);
						String testCaseName = row.getCell(1).getStringCellValue();
						addTestCaseSheet(folderName, moduleName, testCaseName, testCaseList);
					}
				}
			}
		}
		return testCaseList;
	}

	private static void addTestCaseSheet(String folderName, String moduleName, String testCaseName, Collection<File> testCaseList) {
		final File testCase = new File(folderName + "/" + moduleName + "/" + testCaseName);

		final String XLSX = testCaseName + ".xlsx";
		final File xlsxTestCase = new File(folderName + "/" + moduleName + "/" + XLSX);

		final String XLS = testCaseName + ".xls";
		final File xlsTestCase = new File(folderName + "/" + moduleName + "/" + XLS);

		if (xlsxTestCase.exists()) {
			testCaseList.add(xlsxTestCase);
		} else if (xlsTestCase.exists()) {
			testCaseList.add(xlsTestCase);
		} else {
			testCaseList.add(testCase);
		}
	}

	private static void readTestsFromCsvFile(final String folderName, final File testCaseSheet, final Collection<File> testCases) throws IOException {
		final CSVReader reader = new CSVReader(new FileReader(testCaseSheet));
		boolean headerRow = true;
		for (final String[] cells : reader) {
			if (headerRow) {
				// skip header row
				headerRow = false;
			} else if (cells.length == 0 || (cells.length == 1 && cells[0].isEmpty())) {
				// skip empty row
			} else {
				if (cells.length != 2) {
					throw new IOException("Error: invalid row. Format must be: ModuleDirectory,TestCaseExcelFileName");
				}
				final String moduleName = cleanModuleName(cells[0]);
				setClientName(moduleName);
				final String testCaseName = cells[1];
				addTestCaseSheet(folderName, moduleName, testCaseName, testCases);
			}
		}
	}

	private static void setClientName(final String moduleName) {
		// pull out first directory name if using slashes
		// (this will be used as the client name elsewhere)
		ExcelUtils.ModuleName = moduleName.split("/")[0];
	}

	private static String cleanModuleName(final String moduleName) {
		return moduleName.replace('\\', '/');
	}
}
