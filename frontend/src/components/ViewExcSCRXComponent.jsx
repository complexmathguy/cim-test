import React, { Component } from 'react'
import ExcSCRXService from '../services/ExcSCRXService'

class ViewExcSCRXComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            excSCRX: {}
        }
    }

    componentDidMount(){
        ExcSCRXService.getExcSCRXById(this.state.id).then( res => {
            this.setState({excSCRX: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View ExcSCRX Details</h3>
                    <div className = "card-body">
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewExcSCRXComponent
