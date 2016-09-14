use with a wet-boew datatables like this:

<script th:inline="javascript">
    /*<![CDATA[*/
    (function() {
        window["wb-tables"] = {
            "ajax": {
                "url": /*[[@{/api/categories}]]*/ '/api/categories',
                "dataSrc": ""
            },
            columns: [
                {data: "titleEn"},
                {data: "descriptionEn"}
            ]
        }
    })();
    /*]]>*/
</script>
<h1>Dashboard</h1>

    <table class="wb-tables table table-striped table-hover">
        <thead>
        <tr>
            <th>Title</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>title</td>
            <td>description</td>
        </tr>
        </tbody>
    </table>
