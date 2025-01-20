<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
  <table class="table">
    <tr>
      <th>Description</th>
      <th>Target Date</th>
      <th>Is Done?</th>
      <th></th>
      <th></th>
    </tr>
    <c:forEach items="${todos}" var="todo">
      <tr>
        <td>${todo.todoDescription}</td>
        <td>${todo.todoDate}</td>
        <td>${todo.todoDone}</td>
        <td><a href="update-todo?id=${todo.todoId}" class="btn btn-success">UPDATE</a></td>
        <td><a href="delete-todo?id=${todo.todoId}" class="btn btn-warning">DELETE</a></td>
      </tr>
    </c:forEach>
  </table>
  <a href="add-todo" class="btn btn-success">add-todo</a>
</div>
<%@ include file="common/footer.jspf" %>