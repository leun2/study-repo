<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
  <form:form method="post" modelAttribute="todo">
    <fieldset class="mb-3">
      <form:label path="todoDescription">Description:</form:label>
      <form:input type="text" path="todoDescription" required="required" class="form-control"/>
      <form:errors path="todoDescription" cssClass="text-warning"/>
    </fieldset>

    <fieldset class="mb-3">
      <form:label path="todoDate">Date:</form:label>
      <form:input type="text" path="todoDate" class="form-control" required="required" id="todoDate"/>
      <form:errors path="todoDate" cssClass="text-warning"/>
    </fieldset>

    <form:input type="hidden" path="todoId"/>
    <form:input type="hidden" path="todoDone"/>
    <input type="submit" class="btn btn-success"/>
  </form:form>
</div>
<%@ include file="common/footer.jspf" %>

<script>
  $(document).ready(function () {
      $('#todoDate').datepicker({
          format: 'yyyy-mm-dd', // 날짜 형식
          autoclose: true, // 날짜 선택 시 자동으로 닫힘
          todayHighlight: true // 오늘 날짜 강조
      });
  });
</script>