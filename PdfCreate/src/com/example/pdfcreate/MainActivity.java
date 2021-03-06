package com.example.pdfcreate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	
	 private static String FILE = Environment.getExternalStorageDirectory()+"/firstPdf.pdf";
	
	 
	 EditText txt1,txt2;
	 Button btn1;
	 static String str2;
	static String str1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1=(Button)findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
			
					File f=new File(FILE);
					if(!(f.exists()))
					{
						try {
							f.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Log.e("FileCreated", "filsesss");
						}
					}
				
				txt1=(EditText) findViewById(R.id.editText2);
				txt2=(EditText) findViewById(R.id.editText1);
				str1=txt1.getText().toString();
				str2=txt2.getText().toString();
				Log.e("Pdfmssgggg......1..", str1);
				Log.e("Pdfmssgggg....2....", str2);
				try 
				{
						Document document = new Document();
			            PdfWriter.getInstance(document, new FileOutputStream(FILE));
			            document.open();
			            addMetaData(document);
			            addTitlePage(document);
			            addContent(document);
			            //createImage();
			            document.close();
			            Log.e("Pdfmssgggg", "PDG created");
			            Toast.makeText(getApplicationContext(), "Pdf Created",Toast.LENGTH_LONG).show();
				}
				catch (Exception e) 
				{
					Log.e("Errorrr", e.getMessage());
					Toast.makeText(getApplicationContext(), "Error---in Pdf Created",Toast.LENGTH_LONG).show();
				}
				
				
				
				
			}
		});
		
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	
	 private static void addMetaData(Document document) {
	        document.addTitle("My first PDF");
	        document.addSubject("Using iText");
	        document.addKeywords("Java, PDF, iText");
	        document.addAuthor("Lars Vogel");
	        document.addCreator("Lars Vogel");
	    }

	 
	 
	 private static void addTitlePage(Document document)
	            throws DocumentException {
	        Paragraph preface = new Paragraph();
	        // We add one empty line
	        addEmptyLine(preface, 1);
	        // Lets write a big header
	        preface.add(new Paragraph("Details Of the Form"));

	        addEmptyLine(preface, 1);
	        // Will create: Report generated by: _name, _date
	        preface.add(new Paragraph(
	                "Report generated by: " + System.getProperty("Paresh Mutha") + ", " + new Date()));
	        addEmptyLine(preface, 3);
	        preface.add(new Paragraph(
	                "This document describes something which is very important "));

	        addEmptyLine(preface, 8);

	        preface.add(new Paragraph(
	                "This document is a preliminary version and not subject to your license agreement or any other agreement with vogella.de ;-)."));

	        document.add(preface);
	        // Start a new page
	        document.newPage();
	    }
	 
	 
	 
	 private static void addContent(Document document) throws DocumentException {
	        Anchor anchor = new Anchor("ESTIMATING APP");
	        anchor.setName("ESTIMATING APP");

	        // Second parameter is the number of the chapter
	        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

	        Paragraph subPara = new Paragraph("Subcategory 1");
	        Section subCatPart = catPart.addSection(subPara);
	        subCatPart.add(new Paragraph("Hello This Is PDF yeeee"));

	        subPara = new Paragraph("Subcategory 2");
	        subCatPart = catPart.addSection(subPara);
	        
	        subCatPart.add(new Paragraph(str1));
	        subCatPart.add(new Paragraph(str2));
	        subCatPart.add(new Paragraph("Paragraph 3"));

	        // Add a list
	        createList(subCatPart);
	        Paragraph paragraph = new Paragraph();
	        addEmptyLine(paragraph, 5);
	        subCatPart.add(paragraph);

	        // Add a table
	        createTable(subCatPart);

	        // Now add all this to the document
	        document.add(catPart);

	        // Next section
	        anchor = new Anchor("Second Chapter");
	        anchor.setName("Second Chapter");

	        // Second parameter is the number of the chapter
	        catPart = new Chapter(new Paragraph(anchor), 1);

	        subPara = new Paragraph("Subcategory");
	        subCatPart = catPart.addSection(subPara);
	        subCatPart.add(new Paragraph("This is a very important message"));

	        // Now add all this to the document
	        document.add(catPart);

	    }

	    private static void createTable(Section subCatPart)
	            throws BadElementException {
	        PdfPTable table = new PdfPTable(3);

	        // t.setBorderColor(BaseColor.GRAY);
	        // t.setPadding(4);
	        // t.setSpacing(4);
	        // t.setBorderWidth(1);

	        PdfPCell c1 = new PdfPCell(new Phrase("Job Name:"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);

	        c1 = new PdfPCell(new Phrase("Test 001"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);

	        c1 = new PdfPCell(new Phrase(""));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	        table.setHeaderRows(1);

	        table.addCell("Date:");
	        table.addCell("1.1");
	        table.addCell("");
	        table.addCell("Labor Rate:");
	        table.addCell("2.2");
	        table.addCell("");
	        table.addCell("Labor Cost:");
	        table.addCell("3.2");
	        table.addCell("3.3");

	        //subCatPart.add(table);

	    }

	    private static void createList(Section subCatPart) {
	        List list = new List(true, false, 10);
	        list.add(new ListItem("First point"));
	        list.add(new ListItem("Second point"));
	        list.add(new ListItem("Third point"));
	       // subCatPart.add(list);
	    }

	    private static void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	 
	 
	 
	 
	 
	    }
}
