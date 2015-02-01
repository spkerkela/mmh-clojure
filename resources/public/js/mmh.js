/**
 * Created by spkerkela on 01/02/15.
 */

var CommentBox = React.createClass({displayName: 'CommentBox',
    render: function() {
        return (
            React.createElement('h1', {className: "commentBox"},
                "Welcome to MMH."
            )
        );
    }
});
React.render(
    React.createElement(CommentBox, null),
    document.getElementById('content')
);