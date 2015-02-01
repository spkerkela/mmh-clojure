/**
 * Created by spkerkela on 01/02/15.
 */

var Content = React.createClass({displayName: 'CommentBox',
    render: function() {
        return (
            <h1 className="content">
                Welcome to MMH.
            </h1>
        );
    }
});
React.render(
    <Content/>,
    document.getElementById('content')
);