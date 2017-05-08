<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Settings Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet prefetch' href='../static/css/bootstrap.css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h1>Settings Profile</h1>
    <div class="row">
        <div style="width: 623px"
             class="col-xs-12 col-sm-12 col-md-6 col-lg-6 toppad">


            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title"><c:out
                            value="${requestScope.user.firstName} ${requestScope.user.lastName}"/></h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center">
                            <a role="button" data-toggle="modal" data-target="#editAvatar">
                                <img alt="User Pic" src='<c:out value="/user/avatar/${requestScope.user.idUser}"/>'
                                     class="img-circle img-responsive">
                            </a>
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="editAvatar" role="dialog">
                            <div class="modal-dialog">

                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Upload New Avatar</h4>
                                    </div>
                                    <%--<div class="modal-body">--%>
                                    <%--<p>Some text in the modal.</p>--%>
                                    <%--</div>--%>
                                    <div class="modal-footer">
                                        <form action="/user/upload_avatar_by_file" method="post"
                                              enctype="multipart/form-data">
                                            <label class="btn btn-primary">
                                                <input name="uploadedAvatar" required type="file" hidden>
                                            </label>
                                            <button type="submit" class="btn btn-primary">Update Avatar
                                            </button>
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                            </button>
                                        </form>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class=" col-md-9 col-lg-9 ">
                            <form>
                                <table class="table table-user-information">

                                    <tbody>
                                    <tr>
                                        <td><label for="firstName">First Name</label></td>
                                        <td><input id='firstName' value='${requestScope.user.firstName}'></td>
                                    </tr>
                                    <tr>
                                        <td><label for="lastName">Last Name</label></td>
                                        <td><input id='lastName' value='${requestScope.user.lastName}'></td>
                                    </tr>
                                    <tr>
                                        <td><label for="phone">Phone Nomber</label></td>
                                        <td><input id='phone' value='${requestScope.user.phone}'></td>
                                    </tr>
                                    <tr>
                                        <td><label for="birthDate">Date of Birth</label></td>
                                        <td><input id="birthDate" required type="date" name="birthDate"
                                                   value="${requestScope.user.birthDate}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                    <tr>
                                        <td><label>Gender</label></td>
                                        <td>
                                            <div class="form-group">
                                                <div class="cols-sm-10">
                                                    <label class="radio-inline"><input ${requestScope.user.gender=='MALE'?'checked':''}
                                                            required type="radio"
                                                            name="gender"
                                                            value="0">Male</label>
                                                    <label class="radio-inline"><input ${requestScope.user.gender=='FEMALE'?'checked':''}
                                                            required type="radio"
                                                            name="gender"
                                                            value="1">Female</label>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>Email</label></td>
                                        <td><a href="mailto:<c:out value='${requestScope.user.email}'/>"><c:out
                                                value="${requestScope.user.email}"/></a></td>
                                    </tr>
                                    <td><label>Login</label></td>
                                    <td><c:out value='${requestScope.user.login}'/></td>

                                    </tr>

                                    </tbody>

                                </table>

                                <a href="#" onclick="updateUser('/user/update_user_info/')"
                                   class="btn btn-primary">Save changes</a>
                                <a href="/user/index" class="btn btn-primary">Cancel</a>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                    <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button"
                       class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
                    <span class="pull-right">
                            <a href="edit.html" data-original-title="Edit this user" data-toggle="tooltip" type="button"
                               class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
                            <a onclick="postMainDiv('/user/photo_slider')" data-original-title="Remove this user" data-toggle="tooltip" type="button"
                               class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
                        </span>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>

