/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-04-18 15:34:24 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.transaksi;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class pembelian_002dobat_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/jsps/transaksi/../../layouts/taglib.jsp", Long.valueOf(1492264193000L));
    _jspx_dependants.put("/WEB-INF/jsps/transaksi/../../layouts/gritter.jsp", Long.valueOf(1491729162000L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_c_005furl_005f1(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_c_005furl_005f2(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_c_005furl_005f3(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_c_005furl_005f4(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_c_005furl_005f5(_jspx_page_context))
        return;
      out.write('\n');
      if (_jspx_meth_c_005furl_005f6(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"row mt\">\n");
      out.write("\t<div class=\"col-md-12\">\n");
      out.write("\t\t<div class=\"form-panel\">\n");
      out.write("\t\t<form class=\"form formTambah\" id=\"formCari\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${tambahObatUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" method=\"POST\">\n");
      out.write("\t\t\t<h5 class=\"mb\">\n");
      out.write("\t\t\t\t<i class=\"fa fa-angle-right\"></i> Rincian Pembelian\n");
      out.write("\t\t\t</h5>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t<div class=\"col-md-3\">\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<label class=\"lb-sm\">Tanggal Beli</label> <input\n");
      out.write("\t\t\t\t\t\t\t\tname=\"tanggal\" type=\"text\" id=\"tanggal\"\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"form-control datePicker\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${tanggalHariIni}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class=\"col-md-4\">\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<label class=\"lb-sm\">Obat</label> <input name=\"namaObat\"\n");
      out.write("\t\t\t\t\t\t\t\ttype=\"text\" id=\"namaObat\" class=\"form-control\" />\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class=\"col-md-2\">\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<label class=\"lb-sm\">Harga Beli</label> <input name=\"hargaBeli\"\n");
      out.write("\t\t\t\t\t\t\t\ttype=\"text\" id=\"hargaBeli\" class=\"form-control\" />\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t<div class=\"col-md-3\">\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<label class=\"lb-sm\">Nomor Faktur</label> <input\n");
      out.write("\t\t\t\t\t\t\t\tname=\"nomorFaktur\" type=\"text\" id=\"nomorFaktur\"\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"form-control\" />\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class=\"col-md-3\">\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<label class=\"lb-sm\">Kode Obat</label> <input name=\"kodeObat\"\n");
      out.write("\t\t\t\t\t\t\t\ttype=\"text\" id=\"kodeObat\" class=\"form-control\"\n");
      out.write("\t\t\t\t\t\t\t\treadonly=\"readonly\" />\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class=\"col-md-2 col-md-offset-1\">\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<label class=\"lb-sm\">Harga Jual</label> <input name=\"hargaJual\"\n");
      out.write("\t\t\t\t\t\t\t\ttype=\"text\" id=\"hargaJual\" class=\"form-control\" />\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t<div class=\"col-md-3\">\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<label class=\"lb-sm\">Supplier</label> <input type=\"text\"\n");
      out.write("\t\t\t\t\t\t\t\tid=\"supplier\" name=\"supplier\" class=\"form-control\" />\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class=\"col-md-2\">\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<label class=\"lb-sm\">Jumlah Beli</label> <input name=\"jumlah\"\n");
      out.write("\t\t\t\t\t\t\t\ttype=\"number\" id=\"jumlah\" class=\"form-control\" />\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class=\"col-md-2 col-md-offset-2\">\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<label class=\"lb-sm\">Harga Jual Resep</label> <input\n");
      out.write("\t\t\t\t\t\t\t\tname=\"hargaJualResep\" type=\"text\" id=\"hargaJualResep\"\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"form-control\" />\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t<div class=\"col-md-3 col-md-offset-3\">\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<label class=\"lb-sm\">Kadaluarsa</label> <input\n");
      out.write("\t\t\t\t\t\t\t\tname=\"tanggalKadaluarsa\" type=\"text\" id=\"tanggalKadaluarsa\"\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"form-control datePicker\" />\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class=\"col-md-5 col-md-offset-1\">\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<label class=\"lb-sm\">Total</label> <input name=\"subTotal\"\n");
      out.write("\t\t\t\t\t\t\t\ttype=\"text\" id=\"subTotal\" class=\"form-control\" readonly=\"readonly\"\n");
      out.write("\t\t\t\t\t\t\t\tstyle=\"font-size: 20px;\" />\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t<div class=\"col-md-12\">\n");
      out.write("\t\t\t\t\t\t<div class=\"text-right\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"submit\" class=\"btn btn-default\" value=\"Tambahkan\" id=\"tambah\"/>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t</form>\n");
      out.write("\t\t</div>\n");
      out.write("\n");
      out.write("\t\t<div class=\"form-panel\">\n");
      out.write("\t\t<div class=\"row\">\n");
      out.write("\t\t\t<div class=\"col-md-7\">\n");
      out.write("\t\t\t\t<table class=\"table table-striped table-advance table-hover\"\n");
      out.write("\t\t\t\tid=\"tabel\">\n");
      out.write("\t\t\t\t<thead>\n");
      out.write("\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t<th>Obat</th>\n");
      out.write("\t\t\t\t\t\t<th>Jumlah</th>\n");
      out.write("\t\t\t\t\t\t<th>Tanggal Kadaluarsa</th>\n");
      out.write("\t\t\t\t\t\t<th>Harga Beli</th>\n");
      out.write("\t\t\t\t\t\t<th>Sub Total</th>\n");
      out.write("\t\t\t\t\t\t<th></th>\n");
      out.write("\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t</thead>\n");
      out.write("\t\t\t\t<tbody id=\"body\"></tbody>\n");
      out.write("\t\t\t</table>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"col-md-5\">\n");
      out.write("\t\t\t\t<div class=\"form-panel\">\n");
      out.write("\t\t\t\t<h1 id=\"grandTotal\" class=\"text-right\">0</h1>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t\t<div class=\"form-panel\">\n");
      out.write("\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t<div class=\"col-md-6 pull-right\">\n");
      out.write("\t\t\t\t\t<div class=\"text-right\">\n");
      out.write("\t\t\t\t\t\t<input type=\"button\" id=\"simpan\" class=\"btn btn-primary\"\n");
      out.write("\t\t\t\t\t\t\tvalue=\"Simpan\" style=\"width: 50%;\" />\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("<div>\n");
      out.write("\t");
      out.write("\n");
      out.write("<a id=\"gritter-tambah-sukses\" class=\"btn btn-default btn-sm btnHide\" href=\"javascript:;\"></a>\n");
      out.write("<a id=\"gritter-edit-sukses\" class=\"btn btn-default btn-sm btnHide\" href=\"javascript:;\"></a>\n");
      out.write("<a id=\"gritter-hapus-sukses\" class=\"btn btn-default btn-sm btnHide\" href=\"javascript:;\"></a>\n");
      out.write("<a id=\"gritter-tambah-gagal\" class=\"btn btn-default btn-sm btnHide\" href=\"javascript:;\"></a>\n");
      out.write("<a id=\"gritter-edit-gagal\" class=\"btn btn-default btn-sm btnHide\" href=\"javascript:;\"></a>\n");
      out.write("<a id=\"gritter-hapus-gagal\" class=\"btn btn-default btn-sm btnHide\" href=\"javascript:;\"></a>");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("\t$(document).ready(function() {\n");
      out.write("\t\tsetAutoComplete('#namaObat', '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cariObatUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("');\n");
      out.write("\n");
      out.write("\t\t$('#namaObat').blur(function() {\n");
      out.write("\t\t\tvar val = $('#namaObat').val();\n");
      out.write("\t\t\tisiDataObat(val);\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\t\t$(\"#namaObat\").keyup(function(event) {\n");
      out.write("\t\t\tif (event.keyCode == 13) {\n");
      out.write("\t\t\t\tvar val = $('#namaObat').val();\n");
      out.write("\t\t\t\tisiDataObat(val);\n");
      out.write("\t\t\t}\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$('#tanggal').blur(function() {\t\t\t\n");
      out.write("\t\t\t$('#nomorFaktur').focus();\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$('#nomorFaktur').blur(function() {\t\t\t\n");
      out.write("\t\t\t$('#supplier').focus();\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$('#supplier').blur(function() {\t\t\t\n");
      out.write("\t\t\t$('#namaObat').focus();\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$('#jumlah').blur(function() {\t\t\t\n");
      out.write("\t\t\thitungSubTotal();\n");
      out.write("\t\t\t$('#tanggalKadaluarsa').focus();\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$('#tanggalKadaluarsa').blur(function() {\t\t\t\n");
      out.write("\t\t\t$('#hargaBeli').focus();\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$('#hargaBeli').blur(function() {\t\t\t\n");
      out.write("\t\t\thitungSubTotal();\n");
      out.write("\t\t\t$('#hargaJual').focus();\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$('#hargaJual').blur(function() {\t\t\t\n");
      out.write("\t\t\t$('#hargaJualResep').focus();\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$('#hargaJualResep').blur(function() {\t\t\t\n");
      out.write("\t\t\t$('#simpan').focus();\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$(\"#jumlah\").keyup(function(event) {\n");
      out.write("\t\t\tif (event.keyCode == 13) {\n");
      out.write("\t\t\t\thitungSubTotal();\n");
      out.write("\t\t\t}\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$('#tambah').click(function(){\n");
      out.write("\t\t\thitungSubTotal();\n");
      out.write("\t\t\t$(\".formTambah\").validate({\n");
      out.write("\t\t\t\trules : {\n");
      out.write("\t\t\t\t\ttanggal : {\n");
      out.write("\t\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\tsupplier : {\n");
      out.write("\t\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\tnomorFaktur : {\n");
      out.write("\t\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\tnamaObat : {\n");
      out.write("\t\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\tkodeObat : {\n");
      out.write("\t\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\tjumlah : {\n");
      out.write("\t\t\t\t\t\trequired : true,\n");
      out.write("\t\t\t\t\t\tnumber : true,\n");
      out.write("\t\t\t\t\t\tmin : 1\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\ttanggalKadaluarsa : {\n");
      out.write("\t\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t\t},\t\t\t\t\n");
      out.write("\t\t\t\t\thargaJual : {\n");
      out.write("\t\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\thargaJualResep : {\n");
      out.write("\t\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\thargaBeli : {\n");
      out.write("\t\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t\t}\t\t\t\t\n");
      out.write("\t\t\t\t},\n");
      out.write("\t\t\t\tmessages : {\n");
      out.write("\t\t\t\t\ttanggal : \"Isi Tanggal Pembelian\",\n");
      out.write("\t\t\t\t\tsupplier : \"Isi Nama Supplier\",\n");
      out.write("\t\t\t\t\tnomorFaktur : \"Isi Nomor Faktur\",\n");
      out.write("\t\t\t\t\tnamaObat : \"Masukkan Nama Obat\",\n");
      out.write("\t\t\t\t\tkodeObat : \"Masukkan Kode Obat\",\n");
      out.write("\t\t\t\t\ttanggalKadaluarsa : \"Tentukan Tanggal Expire Obat\",\n");
      out.write("\t\t\t\t\tjumlah : {\n");
      out.write("\t\t\t\t\t\trequired : \"Masukkan Jumlah\",\n");
      out.write("\t\t\t\t\t\tnumber : \"Masukkan Angka dengan Benar\",\n");
      out.write("\t\t\t\t\t\tmin : \"Masukkan Lebih dari 0\"\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\thargaJual : {\n");
      out.write("\t\t\t\t\t\trequired : \"Masukkan Harga Jual Obat\"\t\t\t\t\t\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\thargaJualResep : {\n");
      out.write("\t\t\t\t\t\trequired : \"Masukkan Harga Jual Resep Obat\",\t\t\t\t\t\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\thargaBeli : {\n");
      out.write("\t\t\t\t\t\trequired : \"Masukkan Harga Beli Obat\",\t\t\t\t\t\n");
      out.write("\t\t\t\t\t}\t\t\t\t\n");
      out.write("\t\t\t\t},\n");
      out.write("\t\t\t\tsubmitHandler : function(form) {\n");
      out.write("\t\t\t\t\tvar data = {};\n");
      out.write("\t\t\t\t\tdata['obat'] = $('#namaObat').val();\n");
      out.write("\t\t\t\t\tdata['tanggal'] = $('#tanggal').val();\n");
      out.write("\t\t\t\t\tdata['supplier'] = $('#supplier').val();\n");
      out.write("\t\t\t\t\tdata['nomorFaktur'] = $('#nomorFaktur').val();\n");
      out.write("\t\t\t\t\tdata['jumlah'] = $('#jumlah').val();\n");
      out.write("\t\t\t\t\tdata['tanggalKadaluarsa'] = $('#tanggalKadaluarsa').val();\n");
      out.write("\t\t\t\t\tdata['hargaJual'] = $('#hargaJual').val();\n");
      out.write("\t\t\t\t\tdata['hargaJualResep'] = $('#hargaJualResep').val();\n");
      out.write("\t\t\t\t\tdata['hargaBeli'] = $('#hargaBeli').val();\n");
      out.write("\t\t\t\t\tdata['subTotal'] = $('#subTotal').val();\n");
      out.write("\t\t\t\t\t$.postJSON('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${tambahObatUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("', data, function() {\n");
      out.write("\t\t\t\t\t\tclean();\n");
      out.write("\t\t\t\t\t\trefresh();\n");
      out.write("\t\t\t\t\t}, function() {\n");
      out.write("\t\t\t\t\t\t$('#gritter-tambah-gagal').click();\n");
      out.write("\t\t\t\t\t});\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\t$('#simpan').click(function(e){\n");
      out.write("\t\t\tvar tanggal = $('#tanggal').val();\n");
      out.write("\t\t\tvar supplier = $('#supplier').val();\n");
      out.write("\t\t\tvar nomorFaktur = $('#nomorFaktur').val();\n");
      out.write("\t\t\tif (tanggal==null || tanggal==\"\",supplier==null || supplier ==\"\", nomorFaktur == null || nomorFaktur==\"\"){\n");
      out.write("\t\t\t\talert('Harap Isi Tanggal, Nomor Faktur dan Supplier');\n");
      out.write("\t\t\t} else {\t\t\t\n");
      out.write("\t\t\t\tvar data = {};\n");
      out.write("\t\t\t\tdata['tanggal'] = tanggal;\n");
      out.write("\t\t\t\tdata['supplier'] = supplier;\n");
      out.write("\t\t\t\tdata['nomorFaktur'] = nomorFaktur;\t\t\t\t\t\n");
      out.write("\t\t\t\t$.postJSON('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${beliUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("', data, function() {\n");
      out.write("\t\t\t\t\tcleanAll();\n");
      out.write("\t\t\t\t\trefresh();\n");
      out.write("\t\t\t\t\t$('#gritter-tambah-sukses').click();\n");
      out.write("\t\t\t\t}, function() {\n");
      out.write("\t\t\t\t\t$('#gritter-tambah-gagal').click();\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t\te.preventDefault();\n");
      out.write("\t\t\t}\n");
      out.write("\t\t});\n");
      out.write("\t\t\n");
      out.write("\t\tsetMaskingUang(\"#hargaJual\");\n");
      out.write("\t\tsetMaskingUang(\"#hargaJualResep\");\n");
      out.write("\t\tsetMaskingUang(\"#hargaBeli\");\n");
      out.write("\t\tsetMaskingUang(\"#subTotal\");\n");
      out.write("\t});\n");
      out.write("\n");
      out.write("\tfunction isiDataObat(val) {\n");
      out.write("\t\tvar data = {\n");
      out.write("\t\t\tnama : val\n");
      out.write("\t\t};\n");
      out.write("\t\t$.getAjax('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${getObatUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("', data, function(obat) {\n");
      out.write("\t\t\t$('#kodeObat').val(obat.kode);\n");
      out.write("\t\t\t$('#hargaJual').val(obat.obatDetail[0].hargaJual);\n");
      out.write("\t\t\t$('#hargaJual').focus();\n");
      out.write("\t\t\t$('#hargaJualResep').val(obat.obatDetail[0].hargaJualResep);\n");
      out.write("\t\t\t$('#hargaJualResep').focus();\n");
      out.write("\t\t\t$('#hargaBeli').val(obat.obatDetail[0].hargaBeli);\n");
      out.write("\t\t\t$('#hargaBeli').focus();\n");
      out.write("\t\t\t$('#tanggalKadaluarsa').val(\n");
      out.write("\t\t\t\t\tdateFormat(obat.expired[0].tanggalExpired, 'dd-mm-yyyy'));\n");
      out.write("\t\t\t$('#jumlah').focus();\n");
      out.write("\t\t}, null);\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\tfunction hitungSubTotal(){\n");
      out.write("\t\tvar hargaBeli = $('#hargaBeli').val();\t\t\t\n");
      out.write("\t\tvar hargaBeli = hargaBeli.replace(/\\./g, '');\t\t\t\n");
      out.write("\t\tvar jumlah = $('#jumlah').val();\t\t\n");
      out.write("\t\tvar subTotal = hargaBeli * jumlah;\n");
      out.write("\t\tconsole.log(subTotal);\n");
      out.write("\t\t$('#subTotal').val(subTotal);\n");
      out.write("\t\t$('#subTotal').focus();\t\t\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\tfunction refresh() {\n");
      out.write("\t\tvar data = {\n");
      out.write("// \t\t\ts : supplier,\n");
      out.write("// \t\t\tt : tanggal,\n");
      out.write("// \t\t\tn : nomorFaktur\n");
      out.write("\t\t};\n");
      out.write("\n");
      out.write("\t\t$.getAjax('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${daftarTempUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("', data, function(result) {\t\t\t\n");
      out.write("\t\t\t$('#body').empty();\n");
      out.write("\t\t\t$('#body').append(result.tabel);\n");
      out.write("\t\t\t$('#grandTotal').empty();\n");
      out.write("\t\t\t$('#grandTotal').append(result.grandTotal);\n");
      out.write("\t\t}, console.log(''));\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\tfunction hapus(index){\n");
      out.write("\t\tvar data = {};\n");
      out.write("\t\tdata['id'] = index;\n");
      out.write("\t\t$.postJSON('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${hapusObatUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("', data, function() {\n");
      out.write("\t\t\trefresh();\t\t\t\n");
      out.write("\t\t}, function(e) {\n");
      out.write("\t\t\tconsole.log('GAGAL HAPUS');\n");
      out.write("\t\t});\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\tfunction clean(){\n");
      out.write("\t\t$('#kodeObat').val('');\n");
      out.write("\t\t$('#hargaJual').val('');\t\t\n");
      out.write("\t\t$('#hargaJualResep').val('');\t\t\n");
      out.write("\t\t$('#hargaBeli').val('');\t\t\n");
      out.write("\t\t$('#tanggalKadaluarsa').val('');\n");
      out.write("\t\t$('#jumlah').val('');\n");
      out.write("\t\t$('#subTotal').val('');\n");
      out.write("\t\t$('#namaObat').val('');\n");
      out.write("\t\t$('#namaObat').focus();\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\tfunction cleanAll(){\n");
      out.write("\t\t$('#kodeObat').val('');\n");
      out.write("\t\t$('#hargaJual').val('');\t\t\n");
      out.write("\t\t$('#hargaJualResep').val('');\t\t\n");
      out.write("\t\t$('#hargaBeli').val('');\t\t\n");
      out.write("\t\t$('#tanggalKadaluarsa').val('');\n");
      out.write("\t\t$('#jumlah').val('');\n");
      out.write("\t\t$('#subTotal').val('');\n");
      out.write("\t\t$('#namaObat').val('');\n");
      out.write("\t\t$('#supplier').val('');\t\t\n");
      out.write("\t\t$('#nomorFaktur').val('');\n");
      out.write("\t}\n");
      out.write("</script>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005furl_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f0.setParent(null);
    // /WEB-INF/jsps/transaksi/pembelian-obat.jsp(6,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setVar("simpanUrl");
    // /WEB-INF/jsps/transaksi/pembelian-obat.jsp(6,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("/pembelian-obat/tambah");
    int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
    if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f1 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f1.setParent(null);
    // /WEB-INF/jsps/transaksi/pembelian-obat.jsp(7,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f1.setVar("cariObatUrl");
    // /WEB-INF/jsps/transaksi/pembelian-obat.jsp(7,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f1.setValue("/pembelian-obat/cariobat");
    int _jspx_eval_c_005furl_005f1 = _jspx_th_c_005furl_005f1.doStartTag();
    if (_jspx_th_c_005furl_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f2 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f2.setParent(null);
    // /WEB-INF/jsps/transaksi/pembelian-obat.jsp(8,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f2.setVar("getObatUrl");
    // /WEB-INF/jsps/transaksi/pembelian-obat.jsp(8,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f2.setValue("/obat/nama");
    int _jspx_eval_c_005furl_005f2 = _jspx_th_c_005furl_005f2.doStartTag();
    if (_jspx_th_c_005furl_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f3(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f3 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f3.setParent(null);
    // /WEB-INF/jsps/transaksi/pembelian-obat.jsp(9,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f3.setVar("tambahObatUrl");
    // /WEB-INF/jsps/transaksi/pembelian-obat.jsp(9,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f3.setValue("/pembelian-obat/tambahTemp");
    int _jspx_eval_c_005furl_005f3 = _jspx_th_c_005furl_005f3.doStartTag();
    if (_jspx_th_c_005furl_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f4(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f4 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f4.setParent(null);
    // /WEB-INF/jsps/transaksi/pembelian-obat.jsp(10,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f4.setVar("hapusObatUrl");
    // /WEB-INF/jsps/transaksi/pembelian-obat.jsp(10,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f4.setValue("/pembelian-obat/hapusTemp");
    int _jspx_eval_c_005furl_005f4 = _jspx_th_c_005furl_005f4.doStartTag();
    if (_jspx_th_c_005furl_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f5(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f5 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f5.setParent(null);
    // /WEB-INF/jsps/transaksi/pembelian-obat.jsp(11,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f5.setVar("daftarTempUrl");
    // /WEB-INF/jsps/transaksi/pembelian-obat.jsp(11,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f5.setValue("/pembelian-obat/daftarTemp");
    int _jspx_eval_c_005furl_005f5 = _jspx_th_c_005furl_005f5.doStartTag();
    if (_jspx_th_c_005furl_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f6(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f6 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f6.setParent(null);
    // /WEB-INF/jsps/transaksi/pembelian-obat.jsp(12,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f6.setVar("beliUrl");
    // /WEB-INF/jsps/transaksi/pembelian-obat.jsp(12,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f6.setValue("/pembelian-obat/beli");
    int _jspx_eval_c_005furl_005f6 = _jspx_th_c_005furl_005f6.doStartTag();
    if (_jspx_th_c_005furl_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f6);
    return false;
  }
}
