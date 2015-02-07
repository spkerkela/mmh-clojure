/**
 * Created by spkerkela on 01/02/15.
 */

var _data = $.getJSON("/movies", function(data){
        console.log(data);
    });

var Content = React.createClass({displayName: 'CommentBox',
    render: function() {
        return (
            <div>
                <h1 className="content">
                    Welcome to MMH.
                </h1>
                <MovieList movies={_data}/>
            </div>
        );
    }
});

var MovieList = React.createClass({
    displayName: 'MovieList',
    render: function(){
        return <h2>Movies</h2>;
    }
});

React.render(
    <Content/>,
    document.getElementById('content')
);