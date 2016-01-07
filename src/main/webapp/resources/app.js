$(document).ready(function(){
	
	$("#selectFiles").click(function() {
        $.fn.SimpleModal({
            model: "modal",
            title: "Title",
            contents:"<div id='tree'></p>",
            onAppend: showTree
            
        }).addButton("Action button", "btn primary", function() {
            this.hide();
        }).showModal();
    });

	var showTree = function(){
		$('#tree')
		.jstree({
			'core' : {
				'data': [
				         'Simple root node',
				         {
				           'text' : 'Root node 2',
				           'state' : {
				             'opened' : true,
				             'selected' : true
				           },
				           'children' : [
				             { 'text' : 'Child 1' },
				             'Child 2'
				           ]
				        }
				      ],
//				'data' : {
//					'url' : '?operation=get_node',
//					'data' : function (node) {
//						return { 'id' : node.id };
//					}
//				},
				'check_callback' : function(o, n, p, i, m) {
					if(m && m.dnd && m.pos !== 'i') { return false; }
					if(o === "move_node" || o === "copy_node") {
						if(this.get_node(n).parent === this.get_node(p).id) { return false; }
					}
					return true;
				},
				'force_text' : true,
				'themes' : {
					'responsive' : false,
					'variant' : 'small',
					'stripes' : true
				}
			},
			'sort' : function(a, b) {
				return this.get_type(a) === this.get_type(b) ? (this.get_text(a) > this.get_text(b) ? 1 : -1) : (this.get_type(a) >= this.get_type(b) ? 1 : -1);
			},
			
			'types' : {
				'default' : { 'icon' : 'glyphicon glyphicon-folder-close' },
				'file' : { 'valid_children' : [], 'icon' : 'file' }
			},
			'unique' : {
				'duplicate' : function (name, counter) {
					return name + ' ' + counter;
				}
			},
			'plugins' : ['state','dnd','sort','types','contextmenu','unique']
		})
		.on('changed.jstree', function (e, data) {
			if(data && data.selected && data.selected.length) {
				$.get('?operation=get_content&id=' + data.selected.join(':'), function (d) {
					if(d && typeof d.type !== 'undefined') {
						$('#data .content').hide();
						switch(d.type) {
							case 'text':
							case 'txt':
							case 'md':
							case 'htaccess':
							case 'log':
							case 'sql':
							case 'php':
							case 'js':
							case 'json':
							case 'css':
							case 'html':
								$('#data .code').show();
								$('#code').val(d.content);
								break;
							case 'png':
							case 'jpg':
							case 'jpeg':
							case 'bmp':
							case 'gif':
								$('#data .image img').one('load', function () { $(this).css({'marginTop':'-' + $(this).height()/2 + 'px','marginLeft':'-' + $(this).width()/2 + 'px'}); }).attr('src',d.content);
								$('#data .image').show();
								break;
							default:
								$('#data .default').html(d.content).show();
								break;
						}
					}
				});
			}
			else {
				$('#data .content').hide();
				$('#data .default').html('Select a file from the tree.').show();
			}
		});
	} 
	
});	
	 


$(function () {
	$(window).resize(function () {
		var h = Math.max($(window).height() - 0, 420);
		$('#container, #data, #tree, #data .content').height(h).filter('.default').css('lineHeight', h + 'px');
	}).resize();

	
});