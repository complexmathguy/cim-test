import React, { Component } from 'react'
import DiscExcContIEEEDEC3AService from '../services/DiscExcContIEEEDEC3AService';

class CreateDiscExcContIEEEDEC3AComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
        }
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            DiscExcContIEEEDEC3AService.getDiscExcContIEEEDEC3AById(this.state.id).then( (res) =>{
                let discExcContIEEEDEC3A = res.data;
                this.setState({
                });
            });
        }        
    }
    saveOrUpdateDiscExcContIEEEDEC3A = (e) => {
        e.preventDefault();
        let discExcContIEEEDEC3A = {
                discExcContIEEEDEC3AId: this.state.id,
            };
        console.log('discExcContIEEEDEC3A => ' + JSON.stringify(discExcContIEEEDEC3A));

        // step 5
        if(this.state.id === '_add'){
            discExcContIEEEDEC3A.discExcContIEEEDEC3AId=''
            DiscExcContIEEEDEC3AService.createDiscExcContIEEEDEC3A(discExcContIEEEDEC3A).then(res =>{
                this.props.history.push('/discExcContIEEEDEC3As');
            });
        }else{
            DiscExcContIEEEDEC3AService.updateDiscExcContIEEEDEC3A(discExcContIEEEDEC3A).then( res => {
                this.props.history.push('/discExcContIEEEDEC3As');
            });
        }
    }
    

    cancel(){
        this.props.history.push('/discExcContIEEEDEC3As');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add DiscExcContIEEEDEC3A</h3>
        }else{
            return <h3 className="text-center">Update DiscExcContIEEEDEC3A</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                        </div>

                                        <button className="btn btn-success" onClick={this.saveOrUpdateDiscExcContIEEEDEC3A}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                   </div>
            </div>
        )
    }
}

export default CreateDiscExcContIEEEDEC3AComponent
