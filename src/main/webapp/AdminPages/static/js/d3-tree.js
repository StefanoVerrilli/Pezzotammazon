// CODICE DI / CODE BY Jes Fink-Jensen:
// https://medium.com/analytics-vidhya/creating-a-radial-tree-using-d3-js-for-javascript-be943e23b74e

// LICENZA SCONOSCIUTA / UNKNOWN LICENSE

/* INFO BY THE ORIGINAL AUTHOR:
Code adapted from
https://observablehq.com/@d3/Fradial-tidy-tree
https://javadude.wordpress.com/2012/06/20/d3-js-from-tree-to-cluster-and-radial-projection/
https://stackoverflow.com/questions/59783070/d3-v5-rad
 */

let createRadialTree = function (input, width, height) {

    let svg = d3.select('#data-visualization').append('svg')
        .attr('width', width)
        .attr('height', height);

    let diameter = height * 0.75;
    let radius = diameter / 2;

    let tree = d3.tree()
        .size([2*Math.PI, radius])
        .separation(function(a, b) { return (a.parent == b.parent ? 1 : 2) / a.depth; });

    let data = d3.hierarchy(input)

    let treeData = tree(data);

    let nodes = treeData.descendants();
    let links = treeData.links();

    let graphGroup = svg.append('g')
        .attr('transform', "translate("+(width/2)+","+(height/2)+")");

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
        .attr("transform", function(d){
            return `rotate(${d.x * 180 / Math.PI - 90})` + `translate(${d.y}, 0)`;
        });


    node.append("circle").attr("r", 1);

    node.append("text")
        .attr("font-family", "sans-serif")
        .attr("font-size", 12)
        .attr("dx", function(d) { return d.x < Math.PI ? 8 : -8; })
        .attr("dy", ".31em")
        .attr("text-anchor", function(d) { return d.x < Math.PI ? "start" : "end"; })
        .attr("transform", function(d) { return d.x < Math.PI ? null : "rotate(180)"; })
        .text(function(d) { return d.data.name; });
};



createRadialTree(data_input, 800, 800)