<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
  <form:form method="post" modelAttribute="todo">
    <fieldset class="mb-3">
      <form:label path="todo_description">Description:</form:label>
      <form:input type="text" path="todo_description" required="required" class="form-control"/>
      <form:errors path="todo_description" cssClass="text-warning"/>
    </fieldset>

    <fieldset class="mb-3">
      <form:label path="todo_datetime">DateTime:</form:label>
      <form:input type="text" path="todo_datetime" class="form-control" required="required" id="todo_date"/>
      <form:errors path="todo_datetime" cssClass="text-warning"/>
    </fieldset>

    <form:input type="hidden" path="todo_id"/>
    <form:input type="hidden" path="todo_done"/>
    <input type="submit" class="btn btn-success"/>
  </form:form>
</div>

<%@ include file="common/footer.jspf" %>

<script>
  $(document).ready(function () {
      $('#todo_date').datepicker({
          format: 'yyyy-mm-dd', // 날짜 형식
          autoclose: true, // 날짜 선택 시 자동으로 닫힘
          todayHighlight: true // 오늘 날짜 강조
      });
  });
</script>