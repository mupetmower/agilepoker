# jQuery Tabs

Simple jquery dropdown

## Instalation

Download the latest version from GitHub

Include the CSS file in the head section:

```html
<link rel="stylesheet" href="jquery.tabs.css">
```

Include the JS file before the `</body>`:

```html
<script src="jquery.tabs.js"></script>
```

Add initialization

```js
$(document).ready(function() {
	$('[data-dropdown-role="dropdown"]').dropdown();
});
```

And now create the dropdown html like in demo

```html
<div data-tabs-role="tabs" class="tabs tabs_style_default">
	<span class="tabs__description visuallyhidden" data-tabs-role="description">
		Use left and right arrows to navigate between tabs.
	</span>
	<div class="tabs__head">
		<button class="tabs__tab" type="button" data-tabs-target="tab1" data-tabs-role="tab">Tab 1</button>
		<button class="tabs__tab" type="button" data-tabs-target="tab2" data-tabs-role="tab">Tab 2</button>
		<button class="tabs__tab" type="button" data-tabs-target="tab3" data-tabs-role="tab">Tab 3</button>
	</div>
	<div class="tabs__body">
		<div class="tabs__pane" data-tabs-id="tab1">
			<h4>Tab 1 Content demo</h4>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. 
			Perferendis dolorem incidunt accusantium quaerat similique 
			repellat eius veritatis porro dolore quod voluptate, atque ipsa? 
			Odit aspernatur veritatis, ad veniam consequatur! Eveniet.</p>
		</div>
		<div class="tabs__pane" data-tabs-id="tab2">
			<h4>Tab 2 Content demo</h4>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. 
			Perferendis dolorem incidunt accusantium quaerat similique 
			repellat eius veritatis porro dolore quod voluptate, atque ipsa? 
			Odit aspernatur veritatis, ad veniam consequatur! Eveniet.</p>
		</div>
		<div class="tabs__pane" data-tabs-id="tab3">
			<h4>Tab 3 Content demo</h4>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. 
			Perferendis dolorem incidunt accusantium quaerat similique 
			repellat eius veritatis porro dolore quod voluptate, atque ipsa? 
			Odit aspernatur veritatis, ad veniam consequatur! Eveniet.</p>
		</div>
		<div class="tabs__preloader" data-tabs-role="preloader"></div>
	</div>
</div>
```

if you want use custom html, mark parts of your dropdown using data attributes

* `data-tabs-role="tabs"` - Tabs container
* `data-tabs-role="tab"` - Tab
* `data-tabs-role="preloader"` - Preloader
* `data-tabs-role="control"` - Tab control ie button
* `data-tabs-target="tab1"` - What tab panel open
* `data-tabs-id="tab2"` - Tab panel id

## Options

### tabActive
* Type: *String*
* Default: *tabs__tab_active*

Open class name. Add on open tab.

### paneActive
* Type: *String*
* Default: *tabs__pane_active*

Open class name. Add on open pane.


## Events

First get jquery object of your tabs widget

```js
var $tabs = $('[data-tabs-role="tabs"]');
```

### change.tabs

Trigger when tab is changed.

```js
$tabs.on('change.tabs', function(event) {
	event.preventDefault();
	console.log('change');
});
```


## License
[The MIT License (MIT)](LICENSE)
