<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="account"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Database Import and Export -Accounting</title>

		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="assets/css/bootstrap-duallistbox.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-multiselect.min.css" />
		<link rel="stylesheet" href="assets/css/select2.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
		<script src="assets/js/ace-extra.min.js"></script>

	</head>

	<body class="no-skin">
           
		    <%@ include file="Header.jsp" %>
                  
		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

		

			<div class="main-content">
				<div class="main-content-inner">

					<div class="page-content">
						
						<div class="page-header">
							<h1> Database Backup and Restore</h1>
						</div><!-- /.page-header -->
                                         
                                         <div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											                                                                                    
											<div class="space-6"></div>

                                                                <div class="page-header">
							            <h1> BackUp (Export)</h1>
						                </div>
                                                                                        <div>
                                                                                                    <form action="BackupDatabase.html" method="POST">
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
<!--                                                                                                                    <input type="text" name="ePath" class="form-control" placeholder="Export Path">
                                                                                                                    <br>-->
<!--                                                                                                                    <input type="file" name="exportPath" >  
                                                                                                                    <br>-->
                                                                                                                 <div style="padding-left: 125px;">   
                                                                                                                    <input type="submit" class="btn btn-success" value="BackUp" style="padding-left: 11px;">
                                                                                                                 </div>
														</span>
													</label>  
                                                                                                    </form>
                                                                                        </div>           
                                                                                                    
                                                                <div class="page-header">
							            <h1> Restore (Import)</h1>
						                </div>                  
                                                                                         <div>  
                                                                                           
                                                                                                    <form action="RestoreDatabase.html"  method="POST" enctype="multipart/form-data">            
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
<!--                                                                                                                    <input type="text" id="fileName" name="fileName" class="form-control" placeholder="Import Path">
                                                                                                                    <br>-->
                                                                                                                    <input type="file" name="file">
                                                                                                                    <br>
                                                                                                                    <div style="padding-left: 125px;">
                                                                                                                    <input type="submit" class="btn btn-danger" value="Import" style="padding-left: 11px;">
                                                                                                                    </div>
                                                                                                                </span>
													</label>
                                                                                                    </form>   
											</div>   
										</div><!-- /.widget-main -->
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->

							
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->       
						
					</div>
				</div>
			</div><!-- /.main-content -->

			 <%@ include file="Footer.jsp" %>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div>
		<script src="assets/js/jquery-2.1.4.min.js"></script>

		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->
                <script src="assets/js/jquery.dataTables.min.js"></script>
		<script src="assets/js/jquery.dataTables.bootstrap.min.js"></script>
		<script src="assets/js/jquery.bootstrap-duallistbox.min.js"></script>
		<script src="assets/js/jquery.raty.min.js"></script>
		<script src="assets/js/bootstrap-multiselect.min.js"></script>
		<script src="assets/js/select2.min.js"></script>
		<script src="assets/js/jquery-typeahead.js"></script>

		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
 

<script type="text/javascript">
    function selectFolder(e) {
   for (var i = 0; i < e.target.files.length; i++) {
      var s = e.target.files[i].name + '\n';
      s += e.target.files[i].size + ' Bytes\n';
      s += e.target.files[i].type;
      alert(s);
   }
}
</script>    
               
	</body>
</html>
