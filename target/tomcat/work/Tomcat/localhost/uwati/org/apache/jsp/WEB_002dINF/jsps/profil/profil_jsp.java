/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-01-27 09:55:31 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.profil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class profil_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/jsps/profil/../../layouts/gritter.jsp", Long.valueOf(1499867358000L));
    _jspx_dependants.put("/WEB-INF/jsps/profil/../../layouts/taglib.jsp", Long.valueOf(1492264193000L));
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
      out.write("\n");
      out.write("<form class=\"form-horizontal style-form formTambah\" method=\"POST\">\n");
      out.write("\t<div class=\"row\">\n");
      out.write("\t\t<div class=\"col-lg-12 col-md-12\">\n");
      out.write("\t\t\t<div class=\"form-panel\">\n");
      out.write("\t\t\t\t<input type=\"submit\" class=\"btn btn-primary pull-right\"\n");
      out.write("\t\t\t\t\tvalue=\"Simpan\" style=\"width: 250px\"> <input type=\"button\"\n");
      out.write("\t\t\t\t\tclass=\"btn btn-default\" value=\"Reset\" id=\"btnReset\"\n");
      out.write("\t\t\t\t\tstyle=\"width: 110px\">\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"row\">\n");
      out.write("\t\t<div class=\"col-xs-12 col-sm-12 col-md-8 col-lg-8\">\n");
      out.write("\t\t\t<div class=\"form-panel\">\n");
      out.write("\t\t\t\t<h4 class=\"mb\">\n");
      out.write("\t\t\t\t\t<i class=\"fa fa-angle-right\"></i> APOTEK\n");
      out.write("\t\t\t\t</h4>\n");
      out.write("\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t<label class=\"col-sm-2 col-sm-2 control-label\">Nama</label>\n");
      out.write("\t\t\t\t\t<div class=\"col-sm-10\">\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" name=\"nama\" id=\"nama\"\n");
      out.write("\t\t\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${apotek.nama}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" autocomplete=\"off\">\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t<label class=\"col-sm-2 col-sm-2 control-label\">Alamat</label>\n");
      out.write("\t\t\t\t\t<div class=\"col-sm-10\">\n");
      out.write("\t\t\t\t\t\t<textarea class=\"form-control\" rows=\"3\" name=\"alamat\" id=\"alamat\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${apotek.alamat}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</textarea>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t<label class=\"col-sm-2 col-sm-2 control-label\">Telepon</label>\n");
      out.write("\t\t\t\t\t<div class=\"col-sm-10\">\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" name=\"telepon\"\n");
      out.write("\t\t\t\t\t\t\tid=\"telepon\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${apotek.telepon}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" autocomplete=\"off\">\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"col-xs-12 col-sm-12 col-md-4 col-lg-4\">\n");
      out.write("\t\t\t<div class=\"form-panel\">\n");
      out.write("\t\t\t\t<h4 class=\"mb\">\n");
      out.write("\t\t\t\t\t<i class=\"fa fa-angle-right\"></i> Obat\n");
      out.write("\t\t\t\t</h4>\n");
      out.write("\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t<label class=\"col-sm-7 col-sm-7 control-label\">Notifikasi\n");
      out.write("\t\t\t\t\t\tObat Kadaluarsa</label>\n");
      out.write("\t\t\t\t\t<div class=\"col-sm-5\">\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" name=\"tenggat_kadaluarsa\"\n");
      out.write("\t\t\t\t\t\t\tid=\"tenggat_kadaluarsa\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${apotek.tenggatKadaluarsa}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\n");
      out.write("\t\t\t\t\t\t\tautocomplete=\"off\">\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</form>\n");
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
      out.write("<script>\n");
      out.write("\tfunction muatUlang() {\n");
      out.write("\t\t$.getAjax('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dapatkanUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("', null, function(result) {\n");
      out.write("\t\t\t$('#nama').val(result.nama);\n");
      out.write("\t\t\t$('#alamat').val(result.alamat);\n");
      out.write("\t\t\t$('#telepon').val(result.telepon);\n");
      out.write("\t\t\t$('#tenggat_kadaluarsa').val(result.tenggatKadaluarsa);\n");
      out.write("\t\t\t// \t\t\t$('#biaya_resep').val(result.biayaResep);\n");
      out.write("\t\t}, null);\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\t$(document).ready(function() {\n");
      out.write("\t\tmuatUlang();\n");
      out.write("\t\t$(\".formTambah\").validate(\n");
      out.write("\t\t\t{\n");
      out.write("\t\t\t\trules : {\n");
      out.write("\t\t\t\t\tnama : {\n");
      out.write("\t\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\talamat : {\n");
      out.write("\t\t\t\t\t\trequired : true\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\ttenggat_kadaluarsa : {\n");
      out.write("\t\t\t\t\t\trequired : true,\n");
      out.write("\t\t\t\t\t\tnumber : true,\n");
      out.write("\t\t\t\t\t\tmin : 0\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t},\n");
      out.write("\t\t\t\tmessages : {\n");
      out.write("\t\t\t\t\tnama : \"Silahkan Isi Nama Apotik\",\n");
      out.write("\t\t\t\t\talamat : \"Silahkan Isi Alamat Apotik\",\n");
      out.write("\t\t\t\t\ttenggat_kadaluarsa : {\n");
      out.write("\t\t\t\t\t\trequired : \"Batas Hari Peringatan Obat Kadaluarsa Harus Diisi\",\n");
      out.write("\t\t\t\t\t\tnumber : \"Isi dengan Angka\",\n");
      out.write("\t\t\t\t\t\tmin : \"Angka Minimal adalah 0\"\n");
      out.write("\t\t\t\t\t}\n");
      out.write("\t\t\t\t},\n");
      out.write("\t\t\t\tsubmitHandler : function(form) {\n");
      out.write("\t\t\t\t\tvar data = {};\n");
      out.write("\t\t\t\t\t\tdata['nama'] = $('#nama').val();\n");
      out.write("\t\t\t\t\t\tdata['alamat'] = $('#alamat').val();\n");
      out.write("\t\t\t\t\t\tdata['telepon'] = $('#telepon').val();\n");
      out.write("\t\t\t\t\t\tdata['tenggatKadaluarsa'] = $('#tenggat_kadaluarsa').val();\t\t\t\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t$.postJSON('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${simpanUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("',data,\tfunction() {\n");
      out.write("\t\t\t\t\t\t$('#gritter-tambah-sukses').click();\n");
      out.write("\t\t\t\t\t\tmuatUlang();\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\tfunction() {$('#gritter-tambah-gagal').click();});}\n");
      out.write("\t\t\t});\n");
      out.write("\n");
      out.write("\t\t\t$('#btnReset').click(function() {\n");
      out.write("\t\t\t\t$('#nama').val('');\n");
      out.write("\t\t\t\t$('#alamat').val('');\n");
      out.write("\t\t\t\t$('#telepon').val('');\n");
      out.write("\t\t\t\t$('#tenggat_kadaluarsa').val('');\n");
      out.write("\t\t\t});\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t});\n");
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
    // /WEB-INF/jsps/profil/profil.jsp(6,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setVar("simpanUrl");
    // /WEB-INF/jsps/profil/profil.jsp(6,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("/profil/simpan");
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
    // /WEB-INF/jsps/profil/profil.jsp(7,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f1.setVar("dapatkanUrl");
    // /WEB-INF/jsps/profil/profil.jsp(7,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f1.setValue("/profil/dapatkan");
    int _jspx_eval_c_005furl_005f1 = _jspx_th_c_005furl_005f1.doStartTag();
    if (_jspx_th_c_005furl_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
    return false;
  }
}
