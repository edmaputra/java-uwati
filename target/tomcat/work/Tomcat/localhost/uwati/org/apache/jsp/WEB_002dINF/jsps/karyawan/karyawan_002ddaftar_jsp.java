/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-07-25 11:33:12 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.karyawan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class karyawan_002ddaftar_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/jsps/karyawan/../../layouts/gritter.jsp", Long.valueOf(1499867358000L));
    _jspx_dependants.put("/WEB-INF/jsps/karyawan/../../layouts/taglib.jsp", Long.valueOf(1492264193000L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005faccess;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005faccess = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005faccess.release();
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
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"showback\">\n");
      out.write("\t<div class=\"row mt\">\n");
      out.write("\t\t<div class=\"col-md-12\">\n");
      out.write("\t\t\t<div class=\"content-panel\">\n");
      out.write("\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t<div class=\"col-md-2 \">\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_security_005fauthorize_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t\t\t<div class=\"col-md-10\">\n");
      out.write("\t\t\t\t\t\t<form class=\"form-inline pull-right\" id=\"formCari\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" id=\"stringCari\" class=\"form-control\" placeholder=\"Pencarian\" style=\"width: 250px\" />\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" id=\"btnCari\">Cari</button>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\" id=\"btnReset\" onclick=\"refresh(1,'')\">Reset</button>\n");
      out.write("\t\t\t\t\t\t\t</div>\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<br />\n");
      out.write("\n");
      out.write("\t\t\t\t<table class=\"table table-striped table-advance table-hover\" id=\"tabel\">\n");
      out.write("\t\t\t\t</table>\n");
      out.write("\t\t\t\t<div id=\"nav\"></div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"modal fade\" id=\"dokter-modal\" tabindex=\"-1\" role=\"dialog\"\n");
      out.write("\taria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n");
      out.write("\t<div class=\"modal-dialog \">\n");
      out.write("\t\t<div class=\"modal-content\">\n");
      out.write("\t\t\t<div class=\"modal-header\">\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\n");
      out.write("\t\t\t\t\taria-hidden=\"true\">&times;</button>\n");
      out.write("\t\t\t\t<h4 class=\"modal-title\" id=\"myModalLabel\">Karyawan Baru</h4>\n");
      out.write("\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t<form class=\"form style-form formTambah\" method=\"post\">\n");
      out.write("\t\t\t\t<div class=\"form-panel\">\n");
      out.write("\t\t\t\t\t<div class=\"modal-body\">\n");
      out.write("\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label>Nama:</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"nama\" class=\"form-control\" id=\"tambahNama\" autocomplete=\"off\"  />\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-5\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label>Jabatan:</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"jabatan\" class=\"form-control\" id=\"tambahJabatan\" autocomplete=\"off\"  />\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-7\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label>Spesialis:</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"spesialis\" class=\"form-control\" id=\"tambahSpesialis\" autocomplete=\"off\"  />\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-12\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label>SIP:</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"sip\" class=\"form-control\" id=\"tambahSip\" autocomplete=\"off\"  />\t\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-12\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label>Alamat:</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"alamat\" class=\"form-control\" id=\"tambahAlamat\" autocomplete=\"off\"  />\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"hidden\" name=\"id\" class=\"form-control\" id=\"tambahId\" autocomplete=\"off\"  />\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"modal-footer\">\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-default btnKeluar\"\n");
      out.write("\t\t\t\t\t\tdata-dismiss=\"modal\">Keluar</button>\n");
      out.write("\t\t\t\t\t<input type=\"submit\" class=\"btn btn-primary\" value=\"Simpan\" />\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</form>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"modal fade\" id=\"dokter-modal-edit\" tabindex=\"-1\"\n");
      out.write("\trole=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n");
      out.write("\t<div class=\"modal-dialog \">\n");
      out.write("\t\t<div class=\"modal-content\">\n");
      out.write("\t\t\t<div class=\"modal-header\">\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\n");
      out.write("\t\t\t\t\taria-hidden=\"true\">&times;</button>\n");
      out.write("\t\t\t\t<h4 class=\"modal-title\" id=\"myModalLabel\">Edit Karyawan</h4>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<form class=\"form style-form formEdit\" method=\"post\">\n");
      out.write("\t\t\t\t<div class=\"form-panel\">\n");
      out.write("\t\t\t\t\t<div class=\"modal-body\">\n");
      out.write("\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-8\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label>Nama:</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"nama\" class=\"form-control\" id=\"editNama\" autocomplete=\"off\"  />\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-5\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label>Jabatan:</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"jabatan\" class=\"form-control\" id=\"editJabatan\" autocomplete=\"off\"  />\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-7\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label>Spesialis:</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"spesialis\" class=\"form-control\" id=\"editSpesialis\" autocomplete=\"off\"  />\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-12\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label>SIP:</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"sip\" class=\"form-control\" id=\"editSip\" autocomplete=\"off\"  />\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-12\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label>Alamat:</label>\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"alamat\" class=\"form-control\" id=\"editAlamat\" autocomplete=\"off\"  />\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"hidden\" name=\"id\" class=\"form-control\" id=\"editId\" autocomplete=\"off\"  />\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"modal-footer\">\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-default btnKeluar\"\n");
      out.write("\t\t\t\t\t\tdata-dismiss=\"modal\">Keluar</button>\n");
      out.write("\t\t\t\t\t<input type=\"submit\" class=\"btn btn-primary\" value=\"Simpan\" />\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</form>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"modal fade\" id=\"dokter-modal-hapus\" tabindex=\"-1\"\n");
      out.write("\trole=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n");
      out.write("\t<div class=\"modal-dialog modal-sm\">\n");
      out.write("\t\t<div class=\"modal-content\">\n");
      out.write("\t\t\t<div class=\"modal-header\">\n");
      out.write("\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\n");
      out.write("\t\t\t\t\taria-hidden=\"true\">&times;</button>\n");
      out.write("\t\t\t\t<h4 class=\"modal-title\" id=\"myModalLabel\">Hapus Karyawan</h4>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"form-panel\">\n");
      out.write("\t\t\t\t<div class=\"modal-body\">\n");
      out.write("\t\t\t\t\t<p>Apakah Anda Yakin Ingin Menghapus ?</p>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<form class=\"form-horizontal style-form formHapus\" method=\"post\">\n");
      out.write("\t\t\t\t<div class=\"modal-footer\">\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-default btnKeluar\"\n");
      out.write("\t\t\t\t\t\tid=\"keluarModalHapus\" data-dismiss=\"modal\">Tidak</button>\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" class=\"form-control\" id=\"hapusId\" />\t\t\t\t\t\n");
      out.write("\t\t\t\t\t<input type=\"submit\" class=\"btn btn-danger\" value=\"Hapus\" />\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</form>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div>\n");
      out.write("\t");
      out.write("\n");
      out.write("<a id=\"gritter-tambah-sukses\" class=\"btn btn-default btn-sm btnHide\" href=\"javascript:;\"></a>\n");
      out.write("<a id=\"gritter-edit-sukses\" class=\"btn btn-default btn-sm btnHide\" href=\"javascript:;\"></a>\n");
      out.write("<a id=\"gritter-hapus-sukses\" class=\"btn btn-default btn-sm btnHide\" href=\"javascript:;\"></a>\n");
      out.write("<a id=\"gritter-tambah-gagal\" class=\"btn btn-default btn-sm btnHide\" href=\"javascript:;\"></a>\n");
      out.write("<a id=\"gritter-edit-gagal\" class=\"btn btn-default btn-sm btnHide\" href=\"javascript:;\"></a>\n");
      out.write("<a id=\"gritter-hapus-gagal\" class=\"btn btn-default btn-sm btnHide\" href=\"javascript:;\"></a>\n");
      out.write("<a id=\"gritter-trigger\" class=\"btn btn-default btn-sm btnHide\" href=\"javascript:;\"></a>");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("\tfunction getData(ids) {\n");
      out.write("\t\tvar data = {id:ids};\t\t\n");
      out.write("\t\t$.getAjax('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dapatkanUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("', data, function(result) {\n");
      out.write("\t\t\t$('#editNama').val(result.nama);\n");
      out.write("\t\t\t$('#editSpesialis').val(result.spesialis);\n");
      out.write("\t\t\t$('#editSip').val(result.sip);\n");
      out.write("\t\t\t$('#editAlamat').val(result.alamat);\n");
      out.write("\t\t\t$('#editJabatan').val(result.jabatan);\n");
      out.write("\t\t\t$('#editId').val(ids);\n");
      out.write("\t\t}, null);\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\tfunction setIdUntukHapus(ids) {\n");
      out.write("\t\t$('#hapusId').val(ids);\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\tfunction refresh(halaman, find) {\n");
      out.write("\t\tvar data = { hal : halaman, cari : find };\n");
      out.write("\n");
      out.write("\t\t$.getAjax('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${daftarUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("', data, function(result) {\n");
      out.write("\t\t\t$('#tabel').empty();\n");
      out.write("\t\t\t$('#tabel').append(result.tabel);\n");
      out.write("\t\t\t$('#nav').empty();\n");
      out.write("\t\t\t$('#nav').append(result.navigasiHalaman);\n");
      out.write("\t\t}, null);\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\t$(document).ready(function() {\n");
      out.write("\t\trefresh(1, '');\n");
      out.write("\n");
      out.write("\t\t$('#btnCari').click(function() {\n");
      out.write("\t\t\trefresh(1, $('#stringCari').val());\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\t\t$(\".formTambah\").validate({\n");
      out.write("\t\t\trules : {\n");
      out.write("\t\t\t\tnama : {\n");
      out.write("\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t},\n");
      out.write("\t\t\t\tjabatan : {\n");
      out.write("\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t},\n");
      out.write("\t\t\tmessages : {\n");
      out.write("\t\t\t\tnama : \"Nama Wajib Diisi\",\n");
      out.write("\t\t\t\tjabatan : \"Jabatan Wajib Diisi\"\n");
      out.write("\t\t\t},\n");
      out.write("\t\t\tsubmitHandler : function(form) {\n");
      out.write("\t\t\t\tvar data = {};\n");
      out.write("\t\t\t\tdata['nama'] = $('#tambahNama').val();\n");
      out.write("\t\t\t\tdata['spesialis'] = $('#tambahSpesialis').val();\n");
      out.write("\t\t\t\tdata['sip'] = $('#tambahSip').val();\n");
      out.write("\t\t\t\tdata['alamat'] = $('#tambahAlamat').val();\n");
      out.write("\t\t\t\tdata['jabatan'] = $('#tambahJabatan').val();\n");
      out.write("\t\t\t\t$.postJSON('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${tambahUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("', data, function() {\n");
      out.write("\t\t\t\t\t$('#gritter-tambah-sukses').click();\n");
      out.write("\t\t\t\t\t$('.btnKeluar').click();\n");
      out.write("\t\t\t\t\t$('#tambahNama').val('');\n");
      out.write("\t\t\t\t\t$('#tambahSpesialis').val('');\n");
      out.write("\t\t\t\t\t$('#tambahSip').val('');\n");
      out.write("\t\t\t\t\t$('#tambahAlamat').val('');\n");
      out.write("\t\t\t\t\trefresh();\n");
      out.write("\t\t\t\t}, function() {\n");
      out.write("\t\t\t\t\t$('#gritter-tambah-gagal').click();\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t}\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\t\t$(\".formEdit\").validate({\n");
      out.write("\t\t\trules : {\n");
      out.write("\t\t\t\tnama : {\n");
      out.write("\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t},\n");
      out.write("\t\t\t\tjabatan : {\n");
      out.write("\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t},\n");
      out.write("\t\t\tmessages : {\n");
      out.write("\t\t\t\tnama : \"Nama Wajib Diisi\",\n");
      out.write("\t\t\t\tjabatan : \"Jabatan Wajib Diisi\"\n");
      out.write("\t\t\t},\n");
      out.write("\t\t\tsubmitHandler : function(form) {\n");
      out.write("\t\t\t\tvar data = {};\n");
      out.write("\t\t\t\tdata['id'] = $('#editId').val();\n");
      out.write("\t\t\t\tdata['spesialis'] = $('#editSpesialis').val();\n");
      out.write("\t\t\t\tdata['nama'] = $('#editNama').val();\n");
      out.write("\t\t\t\tdata['sip'] = $('#editSip').val();\n");
      out.write("\t\t\t\tdata['alamat'] = $('#editAlamat').val();\n");
      out.write("\t\t\t\tdata['jabatan'] = $('#editJabatan').val();\n");
      out.write("\t\t\t\t$.postJSON('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${editUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("', data, function() {\n");
      out.write("\t\t\t\t\t$('#gritter-edit-sukses').click();\n");
      out.write("\t\t\t\t\t$('.btnKeluar').click();\n");
      out.write("\t\t\t\t\trefresh();\n");
      out.write("\t\t\t\t}, function() {\n");
      out.write("\t\t\t\t\t$('#gritter-edit-gagal').click();\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t}\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\t\t$(\".formHapus\").submit(function(e) {\n");
      out.write("\t\t\te.preventDefault();\n");
      out.write("\t\t\tvar data = {};\n");
      out.write("\t\t\tdata['id'] = $('#hapusId').val();\n");
      out.write("\t\t\t$.postJSON('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${hapusUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("', data, function(result) {\n");
      out.write("\t\t\t\trefresh();\n");
      out.write("\t\t\t\t$('#keluarModalHapus').click();\n");
      out.write("\t\t\t\t$('#gritter-hapus-sukses').click();\n");
      out.write("\t\t\t}, function(e) {\n");
      out.write("\t\t\t\t$('#gritter-hapus-sukses').click();\n");
      out.write("\t\t\t\t$('#keluarModalHapus').click();\n");
      out.write("\t\t\t\trefresh();\n");
      out.write("\t\t\t});\n");
      out.write("\t\t});\n");
      out.write("\n");
      out.write("\t});\n");
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
    // /WEB-INF/jsps/karyawan/karyawan-daftar.jsp(6,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setVar("tambahUrl");
    // /WEB-INF/jsps/karyawan/karyawan-daftar.jsp(6,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("/karyawan/tambah");
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
    // /WEB-INF/jsps/karyawan/karyawan-daftar.jsp(7,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f1.setVar("editUrl");
    // /WEB-INF/jsps/karyawan/karyawan-daftar.jsp(7,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f1.setValue("/karyawan/edit");
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
    // /WEB-INF/jsps/karyawan/karyawan-daftar.jsp(8,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f2.setVar("hapusUrl");
    // /WEB-INF/jsps/karyawan/karyawan-daftar.jsp(8,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f2.setValue("/karyawan/hapus");
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
    // /WEB-INF/jsps/karyawan/karyawan-daftar.jsp(9,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f3.setVar("dapatkanUrl");
    // /WEB-INF/jsps/karyawan/karyawan-daftar.jsp(9,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f3.setValue("/karyawan/dapatkan");
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
    // /WEB-INF/jsps/karyawan/karyawan-daftar.jsp(10,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f4.setVar("daftarUrl");
    // /WEB-INF/jsps/karyawan/karyawan-daftar.jsp(10,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f4.setValue("/karyawan/daftar");
    int _jspx_eval_c_005furl_005f4 = _jspx_th_c_005furl_005f4.doStartTag();
    if (_jspx_th_c_005furl_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f4);
    return false;
  }

  private boolean _jspx_meth_security_005fauthorize_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  security:authorize
    org.springframework.security.taglibs.authz.JspAuthorizeTag _jspx_th_security_005fauthorize_005f0 = (org.springframework.security.taglibs.authz.JspAuthorizeTag) _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005faccess.get(org.springframework.security.taglibs.authz.JspAuthorizeTag.class);
    _jspx_th_security_005fauthorize_005f0.setPageContext(_jspx_page_context);
    _jspx_th_security_005fauthorize_005f0.setParent(null);
    // /WEB-INF/jsps/karyawan/karyawan-daftar.jsp(18,6) name = access type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_security_005fauthorize_005f0.setAccess("hasAnyRole('ADMIN')");
    int _jspx_eval_security_005fauthorize_005f0 = _jspx_th_security_005fauthorize_005f0.doStartTag();
    if (_jspx_eval_security_005fauthorize_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n");
      out.write("\t\t\t\t\t\t\t<button class=\"btn btn-primary\" data-toggle=\"modal\"\n");
      out.write("\t\t\t\t\t\t\t\tdata-target=\"#dokter-modal\">Karyawan Baru</button>\n");
      out.write("\t\t\t\t\t\t");
    }
    if (_jspx_th_security_005fauthorize_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005faccess.reuse(_jspx_th_security_005fauthorize_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fsecurity_005fauthorize_0026_005faccess.reuse(_jspx_th_security_005fauthorize_005f0);
    return false;
  }
}
