<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/Head.jsp">
    <jsp:param name="page_title" value="Classes.Cart.CartModel - Pezzotammazon"/>
</jsp:include>
<script src="https://d3js.org/d3.v6.min.js"></script>
<jsp:include page="/Navbar.jsp">
    <jsp:param name="access_type" value="${user.getAccessType()}"/>
</jsp:include>
<%
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    if (session.getAttribute("user") == null)
        response.sendRedirect("/LogIn.jsp");
%>
<body>

<script type="text/javascript">

  let root = {
    "name": "A", "info": "tst", "children": [
      {
        "name": "A1", "children": [
          {"name": "A12"},
          {"name": "A13"},
          {"name": "A14"},
          {"name": "A15"},
          {"name": "A16"}
        ]
      },
      {
        "name": "A2", "children": [
          {"name": "A21"},
          {
            "name": "A22", "children": [
              {"name": "A221"},
              {"name": "A222"},
              {"name": "A223"},
              {"name": "A224"}
            ]
          },
          {"name": "A23"},
          {"name": "A24"},
          {"name": "A25"}]
      },
      {
        "name": "A3", "children": [
          {
            "name": "A31", "children": [
              {"name": "A311"},
              {"name": "A312"},
              {"name": "A313"},
              {"name": "A314"},
              {"name": "A315"}
            ]
          }]
      }
    ]
  };

  let createRadialTree = function (input) {
    let height = 600;
    let width = 600;

    let svg = d3.select('body')
            .append('svg')
            .attr('width', width)
            .attr('height', height);

    let diameter = height * 0.75;
    let radius = diameter / 2;

    let tree = d3.tree()
            .size([2 * Math.PI, radius])
            .separation(function (a, b) {
              return (a.parent == b.parent ? 1 : 2) / a.depth;
            });

    let data = d3.hierarchy(input)

    let treeData = tree(data);

    let nodes = treeData.descendants();
    let links = treeData.links();

    let graphGroup = svg.append('g')
            .attr('transform', "translate(" + (width / 2) + "," + (height / 2) + ")");

    graphGroup.selectAll(".link")
            .data(links)
            .join("path")
            .attr("class", "link")
            .attr("d", d3.linkRadial()
                    .angle(d => d.x)
                    .radius(d => d.y));

    let node = graphGroup
            .selectAll(".node")
            .data(nodes)
            .join("g")
            .attr("class", "node")
            .attr("transform", function (d) {
              return `rotate(${d.x * 180 / Math.PI - 90})` + `translate(${d.y}, 0)`;
            });


    node.append("circle").attr("r", 1);

    node.append("text")
            .attr("font-family", "sans-serif")
            .attr("font-size", 12)
            .attr("dx", function (d) {
              return d.x < Math.PI ? 8 : -8;
            })
            .attr("dy", ".31em")
            .attr("text-anchor", function (d) {
              return d.x < Math.PI ? "start" : "end";
            })
            .attr("transform", function (d) {
              return d.x < Math.PI ? null : "rotate(180)";
            })
            .text(function (d) {
              return d.data.name;
            });
  };

  createRadialTree(root);
</script>

</body>
</html>
