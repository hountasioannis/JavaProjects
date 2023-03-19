var userId = ''
var movieId = ''
var movieTitle = ''
var arrayFav = []

$(document).ready(function(){

    $('#button1').on('click', ()=>{
        let username = $('#username').val()
        userId = username
        handleUsername(username)
        if (username.length > 0 ) {
            $('.form').addClass('hidden')
            $('.center').removeClass('hidden')
        }
        $('#username').val("")
    })


    $('#back').on('click', function(){
        $('.center').addClass('hidden')
        $('.form').removeClass('hidden')
    })
})




function handleUsername(username) {

    return $('#helloUser').html("hello " + username)

}

$('#favorites').on('click', function() {
    getFavorites(userId)
    $('.user').html("username: " + userId)
    $('.center').addClass('hidden')
    $('.movies').removeClass('hidden')


})

function getFavorites() {
  //  $( ".movies" ).selectable("option", "autoRefresh");
    let ajaxRequest = new XMLHttpRequest()
    ajaxRequest.open("GET", `http://localhost:8080/api/favorites/${userId}`, true)
    ajaxRequest.timeout = 5000
    ajaxRequest.ontimeout = (e) => onAppiError()
    ajaxRequest.onreadystatechange = function() {
        if (ajaxRequest.readyState === 4) {
            if (ajaxRequest.status === 200) {
                arrayFav = JSON.parse(ajaxRequest.responseText)

                for (let i = 0; i < arrayFav.length; i++) {
                    showFavorites(arrayFav[i][0],arrayFav[i][1],arrayFav[i][2])
                }

            } else {
                onAppiError()
            }
        }
    }
    ajaxRequest.send()
}

function showFavorites(user, id, movie) {
    let clone = $('.row.hidden').clone().addClass("toMove")
    clone.removeClass('hidden')
    clone.find('button').removeClass('hidden')

    clone.find('button').on('click', function () {
        $('.toMove').remove()
        let ajaxRequest = new XMLHttpRequest()
        ajaxRequest.open("DELETE", `http://localhost:8080/api/favorites/${user}/${id}`, true)
        ajaxRequest.timeout = 5000
        ajaxRequest.ontimeout = (e) => onAppiError()
        ajaxRequest.onreadystatechange = function() {
            if (ajaxRequest.readyState === 4) {
                if (ajaxRequest.status === 200) {
                     arrayFav = JSON.parse(ajaxRequest.responseText)


                    for (let i = 0; i < arrayFav.length; i++) {
                        showFavorites(arrayFav[i][0],arrayFav[i][1],arrayFav[i][2])
                    }

                } else {
                    onAppiError()
                }
            }
        }
        ajaxRequest.send()
    })

    clone.find('label').html(movie)

    $('.movies').append(clone)
}



    $('#back2').on('click', function(){
     //   $( ".movies" ).selectable( "refresh" );
        $('.toMove').remove()
        $('.movies').addClass('hidden')
        $('.center').removeClass('hidden')
    })




$(document).ready(function() {
    var debounceTimeout = null
    $("#searchInput").on('input', function() {
        clearTimeout(debounceTimeout)
        debounceTimeout = setTimeout(() => getMovie(this.value.trim()), 1500)
    })



    $('#showMore').on('click', function() {
        onShowMoreClicked()
    })

})


function getMovie(title) {
    if (!title) {
        return
    }
    onBeforeSend()
    fetchMovieFromApi(title)
}

function fetchMovieFromApi(title) {
    let ajaxRequest = new XMLHttpRequest()
    ajaxRequest.open("GET", `http://www.omdbapi.com/?t=${title}&apikey=YOURAPIKEY`, true)
    ajaxRequest.timeout = 5000
    ajaxRequest.ontimeout = (e) => onAppiError()
    ajaxRequest.onreadystatechange = function() {
        if (ajaxRequest.readyState == 4) {
            if (ajaxRequest.status === 200) {
                movieTitle = JSON.parse(ajaxRequest.responseText).Title
                movieId = JSON.parse(ajaxRequest.responseText).imdbID
                console.log(userId)
                console.log(movieId)
                console.log(movieTitle)
                handleResults(JSON.parse(ajaxRequest.responseText))

            } else {
                onAppiError()
            }
        }
    }

    ajaxRequest.send()
}



function handleResults(result) {
    if (result.Response === "True") {
        let transformed = transformResponse(result)
        buildMovie(transformed)
    } else if (result.Response === "False") {
        hideComponent('#waiting')
        showNotFound()
    }
}

function buildMovie(apiResponse) {
    if (apiResponse.poster) {
        $('#image').attr('src',apiResponse.poster).on('load', function() {
            buildMovieMetadata(apiResponse, $(this))
        })
    } else {
        buildMovieMetadata(apiResponse)
    }
}

function onBeforeSend() {
    showComponent('#waiting')
    hideComponent('.movie')
    hideNotFound()
    hideError()
    collapsePlot()
    hideExtras()
}

function onAppiError() {
    hideComponent('#waiting')
    showError()
}

function buildMovieMetadata(apiResponse, imageTag) {
    hideComponent('#waiting')
    handleImage(imageTag)
    handleLiterals(apiResponse)
    showComponent('.movie')
}

function handleImage(imageTag) {
    imageTag ? $('#image').replaceWith(imageTag) : $('#image').removeAttr('src')
}

function handleLiterals(apiResponse) {
    $('.movie').find('[id]').each((index, item) => {
        if ($(item).is('a')) {
            $(item).attr('href', apiResponse[item.id])
        } else {
            let valueElement = $(item).children('span')
            let metadataValue = apiResponse[item.id] ? apiResponse[item.id] : '-'
            valueElement.length ? valueElement.text(metadataValue) : $(item).text(metadataValue)
        }
    })
}

function transformResponse(apiResponse) {
    let camelCaseKeyResponse = camelCaseKeys(apiResponse)
    clearNotAvailableInformation(camelCaseKeyResponse)
    buildImdb(camelCaseKeyResponse)
    return camelCaseKeyResponse
}

function camelCaseKeys(apiResponse) {
    return _.mapKeys(apiResponse, (v, k) => _.camelCase(k))
}

function buildImdb(apiResponse) {
    if (apiResponse.imdbId && apiResponse.imdbId !== 'N/A') {
        apiResponse.imdbId = `https://www.imdb.com/title/${apiResponse.imdbId}`

    }
}

function clearNotAvailableInformation(apiResponse) {
    for (var key in apiResponse) {
        if (apiResponse.hasOwnProperty(key) && apiResponse[key] === 'N/A') {
            apiResponse[key] = ''
        }
    }
}

function onShowMoreClicked() {
    $('#plot').toggleClass('expanded')
    if($('.extended').is(":visible")) {
        $('.extended').hide(700)
    } else {
        $('.extended').show(700)
    }
}

function hideComponent(jQuerySelector) {
    return $(jQuerySelector).addClass('hidden')
}

function showComponent(jQuerySelector) {
    return $(jQuerySelector).removeClass('hidden')
}

function showNotFound() {
    $('.not-found').clone().removeClass('hidden').appendTo($('.center'))
}

function hideNotFound() {
    $('.center').find('.not-found').remove()
}

function showError() {
    $('.error').clone().removeClass('hidden').appendTo($('.center'))
}

function hideError() {
    $('.center').find('.error').remove()
}

function hideExtras() {
    $('.extended').hide()
}

function collapsePlot() {
    $('#plot').removeClass('expanded')
}


$('#favoriteLink').on('click', function(){


    sendMovieTofavourites(userId, movieId, movieTitle)
})

function sendMovieTofavourites(userId, movieId, movieTitle) {
    let ajaxRequest = new XMLHttpRequest()
    ajaxRequest.open("POST", `http://localhost:8080/api/favorites`, true)
    ajaxRequest.setRequestHeader("Content-Type", "application/json");
    ajaxRequest.timeout = 5000
    ajaxRequest.ontimeout = (e) => onAppiError()
    ajaxRequest.onreadystatechange = function() {
        if (ajaxRequest.readyState === 4) {
            if (ajaxRequest.status === 200) {
                alert(`${ajaxRequest.responseText}`)
                console.log(ajaxRequest.responseText)
            } else {
                  onAppiError()
            }
        }
    }

    ajaxRequest.send(JSON.stringify({'userId': userId, 'movieId' : movieId,'movieTitle' : movieTitle}))
}


