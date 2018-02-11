
<%@page import="com.accounting.bean.AccessControlMasterTable"%>
<%@page import="com.accounting.bean.AccessControl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Code -Accounting</title>

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

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="assets/js/ace-extra.min.js"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="no-skin">
              <c:set var="listLength" value="${fn:length(listGroup)}"/>
        <c:forEach items="${listGroup}" var="group">
            <c:choose>
                <c:when test="${group.moduleName=='Access Control'}">
                    <p style="display: none"> ${listLength=listLength+1} </p>
                  
                </c:when>
                <c:otherwise>
                    <p style="display: none">  ${listLength=listLength-1}</p>
                    <c:if test="${listLength==0}">
                        <c:redirect url="Dashboard.html"/>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </c:forEach>
		    <%@ include file="Header.jsp" %>

                   
      
        
		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

		

			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>

							<li>
								<a href="#">Access Control</a>
							</li>
							<li class="active"> Create Access Group </li>
						</ul><!-- /.breadcrumb -->
					</div>
                                                <div class="page-header">
							<h1>
									Access Control
							</h1>
						 </div><!-- /.page-header -->

						<div class="row">
						<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
                                                      <div class="form-group  col-md-12" style="padding-top: 20px;">
                             
                        <!-- Table-->
                        
                     <form  name="frmGroupOperations" id="wizardForm" method="POST"  onsubmit="return OnSubmitForm();">
                    
                <div id="main-wrapper">
                    <div class="row">
                        <div class="col-md-12">
                                                        
                <div class="form-group  col-md-12" style="padding-top: 20px;">
                    
                    <input type="hidden" class="form-control col-md-6" name="Group_id"  value="${groupDetails.groupid}" id="exampleInput" placeholder="Group Id" readonly="">
                    
                    
                    <div class="col-md-3">
                    <label for="exampleInputName2">Group Name<span style="color:red;">*</span></label>
                    <input type="text" class="form-control col-md-6" name="Group_Name"  id="groupName" placeholder="Group Name" value="${groupDetails.groupName}" onchange="ajaxGroupNameExistDB()" required>
                    <p style="color: red; font-size: 12px;" id="groupErrorMsg" hidden>GroupName Already Exist.</p>
                    </div>
                    <div class="col-md-3">
                    <label for="exampleInputPassword2">Status<span style="color:red;">*</span></label>
               <select class="form-control" name="Group_status" data-placeholder="Choose Status" tabindex="1" required="true">
                <option value="" selected disabled>Choose here</option>
                <option value="Active" ${'Active' == groupDetails.groupstatus ? 'selected="selected"' : ''} >Active</option>
                <option value="Inactive" ${'Inactive' == groupDetails.groupstatus ? 'selected="selected"' : ''}>Inactive </option> 
                </select>
               </div>    
<!--                    <div class="col-md-4">
                        <button style="margin-top: 24px;" type="submit" id="CreateGroup" class="btn btn-default">save</button>  
                    </div>-->
                     <div class="col-md-4">
                        <button style="margin-top: 24px;" type="submit" id="CreateGroup" class="width-35  btn btn-sm btn-primary bigger-110">save</button>  
                    </div>
                    
                      </div>
                            <div class="panel panel-white">
                                <div class="panel-heading clearfix">
                                    <!--<h4 class="panel-title">Access List</h4>-->
                                </div>
                                 <div class="table-header">
					Access List
			         </div>
                                <div class="panel-body">                             
                                    
                                   <div class="table-responsive">                                                                          
                                    <table class="table table-bordered" id="ass" style="width: 100%; cellspacing: 0;">
                                     <!--<table id="dynamic-table" class="table table-striped table-bordered table-hover">-->   
                                        <thead>
                                            <tr>                                                
                                                <th> <input type="checkbox" id="selectall"/></th>
                                                <th>Access Id</th>                                                
                                                <th>Module Name</th>
                                                <th>Create</th>                                                    
                                                <th>Edit</th>
                                                <th>Delete</th>
                                                <th>View</th>
                                              
                                            </tr>
                                        </thead>                                      
                                        <input type="hidden" name="count" id="count">
                                        
                                       
                                            <tbody>
                                        <%
                                         Object ob=  request.getAttribute("join");
                                         List list1 = (List)(ob);
                                            List<Object> list = new ArrayList<Object>(list1);                                            
                                            int i=0;
                                            
                                            for(Iterator iterator = list.iterator();iterator.hasNext();)
                                            {                                                   
                                                   Object[] objects = (Object[]) iterator.next();         
                                                   AccessControl access = (AccessControl) objects[0];        
                                                   AccessControlMasterTable accesscontrol = (AccessControlMasterTable) objects[1];
                                                   String create="";
                                                   String edit="";
                                                   String delete="";
                                                   String view="";
                                                   String id="";   
                                                   String accesscontrolid="";
                                                     if(accesscontrol!=null){
                                                      create=accesscontrol.getCreatevalue();                                                    
                                                     }
                                                     if(accesscontrol!=null){
                                                      edit=accesscontrol.getEditvalue();                                                     
                                                     }
                                                      if(accesscontrol!=null){
                                                      delete=accesscontrol.getDeletevalue();                                                  
                                                     }
                                                       if(accesscontrol!=null){
                                                      view=accesscontrol.getViewvalue();                                                     
                                                     }
                                                         if(accesscontrol!=null){
                                                      id=String.valueOf(accesscontrol.getAccessid());                                                
                                                     }
                                                         if(accesscontrol!=null){
                                                         accesscontrolid=String.valueOf(accesscontrol.getAccessControlid());
                                                         }
                                                         
                                                    %>
                                            <tr> 
                                                
                                                <td><input type="checkbox" name="Access_id<%=i%>" id="selectrowall<%=i%>" class="checkbox1" onclick="checkPrv(<%=i%>)" value="<%=access.getAccessid()%>" <%= (id!="" ?"checked='checked'":"") %>></td>  
                                                
                                                
                                                <td><%=access.getAccessid()%></td>                                                
                                                <td><%=access.getModuleName()%></td>
                                                <input type="hidden" name="Module_Name<%=i%>" value="<%=access.getModuleName()%>">
               
                                                <td><input type="checkbox" name="create<%=i%>"  id="checkbox1<%=i%>"  class="checkbox1 checkboxrow" value="1"  <%= (create.equals("1")?"checked='checked'":"") %>></td>                                                         
                                                <td><input type="checkbox" name="edit<%=i%>"    id="checkbox2<%=i%>"  class="checkbox1 checkboxrow" value="1"  <%= (edit.equals("1")?"checked='checked'":"") %>></td> 
                                                <td><input type="checkbox" name="delete<%=i%>"  id="checkbox3<%=i%>"  class="checkbox1 checkboxrow" value="1"  <%= (delete.equals("1")?"checked='checked'":"") %>></td>                                                         
                                                <td><input type="checkbox" name="view<%=i%>"    id="checkbox4<%=i%>"  class="checkbox1 checkboxrow" value="1"  <%= (view.equals("1")?"checked='checked'":"") %>></td>                                                
                                            </tr>
                                            <%
                                            i++;  
                                            }
                                            %>   
                                        </tbody> 
                                      
                                    </table>
                                    </div>    
                                </div>                                
                              </div>                              
                         </div>                        
                    </div>
                    <!-- Row -->
                </div>           
                    </form>
                             </div>
                </div>      
                                                 </div>      
                                                </div>            
</div>         
			 <%@ include file="Footer.jsp" %>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="assets/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
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
			jQuery(function($) {
				//initiate dataTables plugin
				var myTable = 
				$('#dynamic-table')
				//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
				.DataTable( {
					
			    } );
			})
		</script>
                  
<script>
$(document).ready(function() {              
    $('#selectall').click(function(event) { //on click
        if(this.checked) { // check select status
              
            $('.checkbox1').each(function() { //loop through each checkbox
                this.checked=true;
            });
        }else{             
            $('.checkbox1').each(function() { //loop through each checkbox
                this.checked = false; //deselect all checkboxes with class "checkbox1"                      
            });        
        }
    }); 
    $('#selectrowall').click(function(event) { //on click
        if(this.checked) { // check select status
              
            $('.checkboxrow').each(function() { //loop through each checkbox
                this.checked=true;
            });
        }else{             
            $('.checkboxrow').each(function() { //loop through each checkbox
                this.checked = false; //deselect all checkboxes with class "checkbox1"                      
            });        
        }
    });
});


function checkPrv(i)
{	
	
        var lsCheckbox =document.getElementById("selectrowall"+i).checked ;
        if(lsCheckbox==false)
	{
	document.getElementById("checkbox1"+i).checked=false;	
        document.getElementById("checkbox2"+i).checked=false;	
        document.getElementById("checkbox3"+i).checked=false;	
        document.getElementById("checkbox4"+i).checked=false;
          
	}
	if(lsCheckbox==true)
	{            	
	document.getElementById("checkbox1"+i).checked=true;	
        document.getElementById("checkbox2"+i).checked=true;	
        document.getElementById("checkbox3"+i).checked=true;	
        document.getElementById("checkbox4"+i).checked=true;	
	}
}
</script>

<script type="text/javascript">
function OnSubmitForm()
{
 var oRows = document.getElementById('ass').getElementsByTagName('tr');
var iRowCount = oRows.length-1;
var elem = document.getElementById("count");
elem.value = iRowCount;
    document.frmGroupOperations.action ="save_AccessControl.html";
  
  return true;
}
</script>
	</body>
</html>
