package edu.richmond.nlp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.util.CoreMap;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationOutputter;

public class CSVDocumentOutputter extends AnnotationOutputter {

  private CSVDocumentDocumentWriter csvWriter = new CSVDocumentDocumentWriter("0");
  private boolean append = false;

  public CSVDocumentOutputter(String docID, boolean append, String language, String starttime, String uri, String version) {
    this.csvWriter.setDocID(docID);
    this.append = append;
    this.csvWriter.setLanguage(language);
    this.csvWriter.setStartTime(starttime);
    this.csvWriter.setURI(uri);
    this.csvWriter.setVersion(version);
  }

  @Override
  public void print(Annotation doc, OutputStream target, Options options) throws IOException {
    PrintWriter writer = new PrintWriter(IOUtils.encodedOutputStreamWriter(target, options.encoding));
    if (!append) {
      writer.print(csvWriter.header);
    }
    writer.print(csvWriter.print(doc));
    writer.flush();
  }

}

