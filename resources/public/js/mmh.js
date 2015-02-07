/**
 * Created by spkerkela on 01/02/15.
 */


var Content = React.createClass({
    loadMovies: function() {
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            success: function(data) {
                this.setState({data: data});
            }.bind(this),
            error: function(xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    },
    getInitialState: function() {
        return {data: []};
    },
    componentDidMount: function() {
        this.loadMovies();
        setInterval(this.loadMovies, 1000);
    },
    render: function() {
        return (
            <div>
                <h1 className="content">
                    Welcome to MMH.
                </h1>
                <MovieList movies={this.state.data}/>
            </div>
        );
    }
});

var MovieList = React.createClass({
    displayName: 'MovieList',
    render: function(){

        var movies = this.props.movies.map(function (movie) {
            return(
                <div>
                    <strong>{movie.title}</strong>
                    <p>{movie.plot}</p>
                </div>
            );
        });

        return (
            <div>
                <h3>Movies</h3>
                {movies}
            </div>
        );
    }
});

var Movie = React.createClass({
    render: function() {
        return (
            <div>
                <strong>{this.props.title}</strong>
                <p>{this.props.plot}</p>
            </div>
        );
    }
});

React.render(
    <Content url="/json/movies" />,
    document.getElementById('content')
);